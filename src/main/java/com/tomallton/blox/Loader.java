package com.tomallton.blox;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.LocalVariableNode;
import org.objectweb.asm.tree.MethodNode;
import org.reflections.Reflections;

import com.tomallton.blox.parser.Entry;
import com.tomallton.blox.parser.Parser;
import com.tomallton.blox.util.FileUtils;

public class Loader {

    private File folder;

    private final Map<String, Class<? extends Block>> blockTypes = new HashMap<>();

    private final Map<Class<? extends Block>, Map<Constructor<?>, List<String>>> constructors = new HashMap<>();

    public void load() throws IllegalStateException {
        if (folder == null) {
            throw new IllegalStateException("Folder not set.");
        } else if (!folder.isDirectory()) {
            throw new IllegalStateException(folder.getPath() + " not a folder.");
        }

        loadPrograms(FileUtils.getFiles(folder));
    }

    private void loadPrograms(Collection<File> files) {
        for (File file : files) {
            if (file.getName().endsWith(".blox")) {
                loadProgram(file);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void loadProgram(File file) {
        List<String> lines = FileUtils.readFile(file);
        Object data;

        try {
            data = new Parser(new ByteArrayInputStream(String.join("", lines).getBytes())).parse();
        } catch (Exception exception) {
            System.err.println("Formatting error in " + file.getName());
            exception.printStackTrace();
            return;
        }

        if (!(data instanceof LinkedList)) {
            System.err.println(file.getName() + " does not contain a blox object");
            return;
        }

        Program program = new Program();
        List<Entry<String, Object>> objects = (LinkedList<Entry<String, Object>>) data;

        for (Entry<String, Object> entry : objects) {
            Class<?> blockClass = blockTypes.get(entry.getKey().toLowerCase());

            if (blockClass == null) {
                System.err.println("Block '" + entry.getKey() + "' does not exist");
                continue;
            }

            List<Object> parameters = new ArrayList<>();

            Object value = extractValue(entry.getValue());

            if (value != null) {
                // value (string, number, or boolean)
                parameters.add(value);
            } else if (entry.getValue() instanceof ArrayList) {
                // array
                for (Object parameter : (List<Object>) entry.getValue()) {
                    parameters.add(extractParameter(parameter));
                }
            } else if (entry.getValue() instanceof LinkedList) {
                // object
                Map<String, Object> object = ((List<Entry<String, Object>>) entry.getValue()).stream().collect(Collectors.toMap(e -> e.getKey().toLowerCase(), Entry::getValue));

                constructors: for (Map.Entry<Constructor<?>, List<String>> constructor : constructors.get(blockClass).entrySet()) {
                    if (Collections.disjoint(object.keySet(), constructor.getValue())) {
                        continue;
                    }

                    Class<?>[] parameterTypes = constructor.getKey().getParameterTypes();

                    for (int i = 0; i < parameterTypes.length; i++) {
                        Object suppliedParameter = extractParameter(object.get(constructor.getValue().get(i)));

                        if (!suppliedParameter.getClass().equals(parameterTypes[i])) {
                            continue constructors;
                        }
                    }

                    try {
                        program.addBlock((Block) constructor.getKey().newInstance(parameters));
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }

                    break;
                }

                continue;
            }

            try {
                blockClass.getConstructor(parameters.stream().map(Object::getClass).toArray(len -> new Class<?>[len])).newInstance(parameters.toArray(new Object[parameters.size()]));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    private Object extractParameter(Object parameter) {
        Object parameterValue = extractValue(parameter);
        if (parameterValue != null) {
            return parameter;
        } else if (parameter instanceof ArrayList) {
            @SuppressWarnings("unchecked")
            List<Object> list = (List<Object>) parameter;
            for (int i = 0; i < list.size(); i++) {
                list.set(i, extractParameter(list.get(i)));
            }
        } else if (parameter instanceof LinkedList) {

        }
        throw new IllegalArgumentException("Invalid parameter " + parameter);
    }

    private Object extractValue(Object value) {
        if (value instanceof String) {
            return value;
        } else if (value instanceof BigInteger) {
            return ((BigInteger) value).intValue();
        } else if (value instanceof BigDecimal) {
            return ((BigDecimal) value).doubleValue();
        }
        return null;
    }

    public void setFolder(File folder) {
        this.folder = folder;
    }

    public void addBlockType(String packageName) {
        new Reflections(packageName).getSubTypesOf(Block.class).forEach(this::addBlockType);
    }

    public void addBlockType(Class<? extends Block> blockType) {
        blockTypes.put(blockType.getSimpleName().toLowerCase(), blockType);

        Map<Constructor<?>, List<String>> constructors = new HashMap<>();
        for (Constructor<?> constructor : blockType.getConstructors()) {
            try {
                List<String> parameterNames = getConstructorParameterNames(constructor).stream().map(String::toLowerCase).collect(Collectors.toList());

                for (int i = 0; i < parameterNames.size(); i++) {
                    for (int j = 0; j < parameterNames.size(); j++) {
                        if (i != j && parameterNames.get(i).equals(parameterNames.get(j))) {
                            throw new IllegalArgumentException("Block " + blockType + " has a constructor with two parameters named the same (but with different case)");
                        }
                    }
                }

                constructors.put(constructor, parameterNames);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }

        this.constructors.put(blockType, constructors);
    }

    private List<String> getConstructorParameterNames(Constructor<?> constructor) throws IOException {
        Class<?> declaringClass = constructor.getDeclaringClass();
        ClassLoader declaringClassLoader = declaringClass.getClassLoader();

        Type declaringType = Type.getType(declaringClass);
        String constructorDescriptor = Type.getConstructorDescriptor(constructor);
        String url = declaringType.getInternalName() + ".class";

        InputStream classFileInputStream = declaringClassLoader.getResourceAsStream(url);
        if (classFileInputStream == null) {
            throw new IllegalArgumentException("The constructor's class loader cannot find the bytecode that defined the constructor's class (URL: " + url + ")");
        }

        ClassNode classNode;
        try {
            classNode = new ClassNode();
            ClassReader classReader = new ClassReader(classFileInputStream);
            classReader.accept(classNode, 0);
        } finally {
            classFileInputStream.close();
        }

        List<MethodNode> methods = classNode.methods;
        for (MethodNode method : methods) {
            if (method.name.equals("<init>") && method.desc.equals(constructorDescriptor)) {
                Type[] argumentTypes = Type.getArgumentTypes(method.desc);
                List<String> parameterNames = new ArrayList<String>(argumentTypes.length);

                List<LocalVariableNode> localVariables = method.localVariables;
                for (int i = 0; i < argumentTypes.length; i++) {
                    // the first local variable actually represents the "this" object
                    parameterNames.add(localVariables.get(i + 1).name);
                }

                return parameterNames;
            }
        }

        return null;
    }

}
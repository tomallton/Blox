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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.LocalVariableNode;
import org.objectweb.asm.tree.MethodNode;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import com.tomallton.blox.parser.Entry;
import com.tomallton.blox.parser.Parser;
import com.tomallton.blox.util.FileUtils;

public class Loader<C> {

    private final Map<String, Class<?>> blockTypes = new HashMap<>();

    private final Map<Class<?>, Map<Constructor<?>, List<String>>> constructors = new HashMap<>();

    private final Map<Class<?>, Class<?>> classCasts = new HashMap<>(Map.of(ArrayList.class, List.class, LinkedList.class, List.class, Double.class, double.class, Integer.class, int.class));

    public void load(File folder) throws IllegalStateException {
        Objects.requireNonNull(folder);

        if (!folder.isDirectory()) {
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

        Program<C> program = new Program<C>();
        List<Entry<String, Object>> objects = (LinkedList<Entry<String, Object>>) data;

        blocks: for (Entry<String, Object> entry : objects) {
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
                Map<String, Object> object = new HashMap<>();
                for (Entry<String, Object> keyValue : (List<Entry<String, Object>>) entry.getValue()) {
                    object.putIfAbsent(keyValue.getKey().toLowerCase(), extractParameter(keyValue.getValue()));
                }

                constructors: for (Map.Entry<Constructor<?>, List<String>> constructor : constructors.get(blockClass).entrySet()) {
                    if (!object.keySet().equals(constructor.getValue().stream().collect(Collectors.toSet()))) {
                        continue;
                    }

                    Class<?>[] parameterTypes = constructor.getKey().getParameterTypes();

                    for (int i = 0; i < parameterTypes.length; i++) {
                        Object suppliedParameter = extractParameter(object.get(constructor.getValue().get(i)));

                        if (!suppliedParameter.getClass().equals(parameterTypes[i])) {
                            continue constructors;
                        }

                        parameters.add(suppliedParameter);
                    }

                    try {
                        program.addBlock(constructor.getKey().newInstance(parameters.toArray(new Object[parameters.size()])));
                    } catch (Exception exception) {
                        System.err.println("Error initialising " + blockClass.getSimpleName());
                        exception.printStackTrace();
                    }

                    continue blocks;
                }

                System.err.println("Error loading " + blockClass.getSimpleName() + ", invalid parameters " + object);
                continue;
            }

            try {
                blockClass.getConstructor(parameters.stream().map(Object::getClass).map(c -> classCasts.getOrDefault(c, c)).toArray(len -> new Class<?>[len]))
                        .newInstance(parameters.toArray(new Object[parameters.size()]));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    private Object extractParameter(Object parameter) {
        Object parameterValue = extractValue(parameter);
        if (parameterValue != null) {
            return parameterValue;
        } else if (parameter instanceof ArrayList) {
            @SuppressWarnings("unchecked")
            List<Object> list = (List<Object>) parameter;
            for (int i = 0; i < list.size(); i++) {
                list.set(i, extractParameter(list.get(i)));
            }
            return list;
        }
        throw new IllegalArgumentException("Invalid parameter " + parameter + " (" + parameter.getClass() + ")");
    }

    private Object extractValue(Object value) {
        if (value instanceof String || value instanceof Integer || value instanceof Double) {
            return value;
        } else if (value instanceof BigInteger) {
            return ((BigInteger) value).intValue();
        } else if (value instanceof BigDecimal) {
            return ((BigDecimal) value).doubleValue();
        }
        return null;
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

    public void addBlockType(String packageName) {
        List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
        classLoadersList.add(ClasspathHelper.contextClassLoader());
        classLoadersList.add(ClasspathHelper.staticClassLoader());

        ConfigurationBuilder config = new ConfigurationBuilder();
        // don't exclude Object.class
        config.setScanners(new SubTypesScanner(false), new ResourcesScanner());
        // filter package
        config.filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix(packageName)));
        config.setUrls(ClasspathHelper.forJavaClassPath());

        new Reflections(config).getSubTypesOf(Object.class).forEach(this::addBlockType);
    }

    public void addBlockType(Class<?> blockType) {
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

    public void addCast(Class<?> from, Class<?> to) {
        classCasts.put(from, to);
    }

    public void removeCast(Class<?> from) {
        classCasts.remove(from);
    }
}
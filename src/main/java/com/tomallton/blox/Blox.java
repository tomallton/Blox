package com.tomallton.blox;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
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
import java.util.function.Function;
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

public class Blox<C> {

    private final Map<String, Class<?>> blocks = new HashMap<>();

    private final Map<Class<?>, Map<Constructor<?>, List<String>>> blockConstructors = new HashMap<>();

    private final Map<Class<?>, Class<?>> paramCasts = new HashMap<>(
            Map.of(ArrayList.class, List.class, LinkedList.class, List.class, Double.class, double.class, Integer.class, int.class, Boolean.class, boolean.class));

    public void load(File folder) {
        Objects.requireNonNull(folder);

        if (!folder.isDirectory()) {
            throw new IllegalArgumentException(folder.getPath() + " not a folder.");
        }

        load(FileUtils.getFiles(folder));
    }

    public void load(Collection<File> files) {
        for (File file : files) {
            if (file.getName().endsWith(".blox") || file.getName().endsWith(".json")) {
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
            throw new IllegalStateException("Invalid JSON in file '" + file.getName() + "'");
        }

        if (!(data instanceof LinkedList)) {
            throw new IllegalStateException("JSON in file '" + file.getName() + "' does not contain a JSON object");
        }

        Program<C> program = new Program<C>();

        for (Entry<String, Object> entry : (List<Entry<String, Object>>) data) {
            Object block = getBlock(entry.getKey(), entry.getValue());

            if (block instanceof ClientBlock) {
                program.addBlock((ClientBlock<C>) block);
            } else {
                program.addBlock(block);
            }
        }
    }

    private Object getBlock(String name, Object value) {
        Class<?> blockClass = blocks.get(name.toLowerCase());

        if (blockClass == null) {
            throw new IllegalArgumentException("Block '" + name + "' does not exist");
        }

        return getBlock(blockClass, value);
    }

    @SuppressWarnings("unchecked")
    private Object getBlock(Class<?> blockClass, Object value) {
        Constructor<?> constructor = null;
        List<Object> parameters = new ArrayList<>();

        Object primitiveValue = extractValue(value);

        if (primitiveValue != null) {
            // value (string, number, or boolean)
            parameters.add(primitiveValue);
        } else if (value instanceof ArrayList) {
            // array
            for (Object parameter : (List<Object>) value) {
                parameters.add(extractParameter(parameter, false));
            }
        } else if (value instanceof LinkedList) {
            // object
            Map<String, Object> object = new HashMap<>();
            for (Entry<String, Object> keyValue : (List<Entry<String, Object>>) value) {
                object.putIfAbsent(keyValue.getKey().toLowerCase(), extractParameter(keyValue.getValue(), true));
            }

            constructors: for (Map.Entry<Constructor<?>, List<String>> constructorEntry : blockConstructors.get(blockClass).entrySet()) {
                if (!object.keySet().equals(constructorEntry.getValue().stream().collect(Collectors.toSet()))) {
                    continue;
                }

                Class<?>[] paramTypes = constructorEntry.getKey().getParameterTypes();

                for (int i = 0; i < paramTypes.length; i++) {
                    Object suppliedParameter = object.get(constructorEntry.getValue().get(i));

                    if (suppliedParameter instanceof LinkedList) {
                        suppliedParameter = getBlock(paramTypes[i], suppliedParameter);
                    } else {
                        suppliedParameter = convertNumber(paramTypes[i], extractParameter(suppliedParameter, false));
                    }

                    if (!paramCasts.getOrDefault(suppliedParameter.getClass(), suppliedParameter.getClass()).equals(paramTypes[i])) {
                        continue constructors;
                    }

                    parameters.add(suppliedParameter);
                }

                constructor = constructorEntry.getKey();
                break;
            }

            if (constructor == null) {
                throw new IllegalArgumentException("Error loading " + blockClass.getSimpleName() + ", invalid parameters " + object);
            }
        }

        if (constructor == null) {
            Function<Boolean, Constructor<?>> findConstructor = lenient -> {
                search: for (Constructor<?> c : blockClass.getConstructors()) {
                    int numParameters = c.getParameterTypes().length;
                    if (numParameters > 0 && c.getParameterTypes()[c.getParameterTypes().length - 1].isArray()) {
                        numParameters--;
                    }

                    if (numParameters > parameters.size()) {
                        continue;
                    }

                    for (int i = 0; i < Math.min(c.getParameterTypes().length, parameters.size()); i++) {
                        Class<?> requiredParameter = c.getParameterTypes()[i];
                        boolean array = requiredParameter.isArray() && i == c.getParameterTypes().length - 1;
                        requiredParameter = array ? requiredParameter.getComponentType() : requiredParameter;

                        for (int j = i; j < (array ? parameters.size() : i + 1); j++) {
                            Class<?> suppliedParameter = parameters.get(j).getClass();
                            suppliedParameter = paramCasts.getOrDefault(suppliedParameter, suppliedParameter);

                            if (!suppliedParameter.equals(requiredParameter)) {
                                if (lenient) {
                                    Object number = convertNumber(requiredParameter, parameters.get(j));
                                    if (number != null) {
                                        parameters.set(j, number);
                                        continue;
                                    }
                                }
                                continue search;
                            }
                        }
                    }

                    return c;
                }
                return null;
            };

            constructor = findConstructor.apply(false);

            if (constructor == null) {
                constructor = findConstructor.apply(true);

                if (constructor == null) {
                    throw new IllegalArgumentException("Error loading " + blockClass.getSimpleName() + ", invalid parameters " + parameters);
                }
            }
        }

        // fix array
        Class<?>[] paramTypes = constructor.getParameterTypes();
        if (paramTypes.length > 0 && paramTypes[paramTypes.length - 1].isArray()) {
            List<Object> typeArray = new ArrayList<>();

            if (parameters.size() >= paramTypes.length) {
                for (int i = paramTypes.length - 1; i < parameters.size(); i++) {
                    typeArray.add(parameters.get(i));
                }
                for (int i = parameters.size() - 1; i >= paramTypes.length - 1; i--) {
                    parameters.remove(i);
                }
            }

            Class<?> arrayTest = paramTypes[paramTypes.length - 1];

            if (arrayTest.getComponentType().isPrimitive()) {
                parameters.add(createPrimitiveArray(typeArray, arrayTest));
            } else {
                parameters.add(typeArray.toArray((Object[]) Array.newInstance(arrayTest.getComponentType(), typeArray.size())));
            }
        }

        try {
            return constructor.newInstance(parameters.toArray(new Object[parameters.size()]));
        } catch (Exception exception) {
            throw new IllegalArgumentException("Error loading " + blockClass.getSimpleName() + ", invalid parameters " + parameters);
        }
    }

    private Object convertNumber(Class<?> requiredParameter, Object parameter) {
        Class<?> suppliedParameter = parameter.getClass();

        if (requiredParameter == double.class) {
            if (suppliedParameter == int.class || suppliedParameter == Integer.class) {
                return (double) ((int) parameter);
            }
        } else if (requiredParameter == float.class) {
            if (suppliedParameter == int.class || suppliedParameter == Integer.class) {
                return (float) ((int) parameter);
            } else if (suppliedParameter == double.class || suppliedParameter == Double.class) {
                return (float) ((double) parameter);
            }
        }

        return parameter;
    }

    private Object createPrimitiveArray(List<Object> array, Class<?> primitiveType) {
        if (primitiveType.equals(boolean.class)) {
            boolean[] primitiveArray = new boolean[array.size()];
            for (int i = 0; i < array.size(); i++) {
                primitiveArray[i] = (boolean) array.get(i);
            }
            return primitiveArray;
        } else if (primitiveType.equals(int.class)) {
            int[] primitiveArray = new int[array.size()];
            for (int i = 0; i < array.size(); i++) {
                primitiveArray[i] = (int) array.get(i);
            }
            return primitiveArray;
        } else if (primitiveType.equals(float.class)) {
            float[] primitiveArray = new float[array.size()];
            for (int i = 0; i < array.size(); i++) {
                primitiveArray[i] = (float) array.get(i);
            }
            return primitiveArray;
        } else if (primitiveType.equals(double.class)) {
            double[] primitiveArray = new double[array.size()];
            for (int i = 0; i < array.size(); i++) {
                primitiveArray[i] = (double) array.get(i);
            }
            return primitiveArray;
        }
        return null;
    }

    private Object extractParameter(Object parameter, boolean lenient) {
        Object parameterValue = extractValue(parameter);
        if (parameterValue != null) {
            return parameterValue;
        } else if (parameter instanceof ArrayList) {
            @SuppressWarnings("unchecked")
            List<Object> list = (List<Object>) parameter;
            for (int i = 0; i < list.size(); i++) {
                list.set(i, extractParameter(list.get(i), false));
            }
            return list;
        }
        if (!lenient) {
            throw new IllegalArgumentException("Invalid parameter " + parameter + " (" + parameter.getClass() + ")");
        } else {
            return parameter;
        }
    }

    private Object extractValue(Object value) {
        if (value instanceof String || value instanceof Integer || value instanceof Double || value instanceof Boolean) {
            return value;
        } else if (value instanceof Integer) {
            return ((Integer) value).intValue();
        }

        else if (value instanceof BigInteger) {
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
        blocks.put(blockType.getSimpleName().toLowerCase(), blockType);

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

        this.blockConstructors.put(blockType, constructors);
    }

    public void addCast(Class<?> from, Class<?> to) {
        paramCasts.put(from, to);
    }

    public void removeCast(Class<?> from) {
        paramCasts.remove(from);
    }
}
package com.tomallton.blox;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.LocalVariableNode;
import org.objectweb.asm.tree.MethodNode;

import com.tomallton.blox.parser.Entry;
import com.tomallton.blox.parser.Parser;
import com.tomallton.blox.util.ClassUtils;
import com.tomallton.blox.util.FileUtils;

public class Blox<C> {

    // block name in lower case to block class
    private final Map<String, Class<?>> blocks = new HashMap<>();

    // block class to its constructors with corresponding parameter names
    private final Map<Class<?>, Map<Constructor<?>, List<String>>> blockConstructors = new HashMap<>();

    // class types to cast to corresponding interface / primitive type
    private final Map<Class<?>, Class<?>> paramCasts = new HashMap<>(
            Map.of(ArrayList.class, List.class, LinkedList.class, List.class, Double.class, double.class, Integer.class, int.class, Boolean.class, boolean.class));

    // all scripts currently loaded
    private final Set<Script<C>> scripts = new HashSet<>();

    public Blox() {
        this(true);
    }

    public Blox(boolean registerDefaultBlocks) {
        if (registerDefaultBlocks) {
            addBlocks("com.tomallton.blox.blocks");
        }
    }

    public List<Script<C>> load(String folder) {
        return load(new File(folder));
    }

    public List<Script<C>> load(File folder) {
        Objects.requireNonNull(folder);

        if (!folder.isDirectory()) {
            throw new IllegalArgumentException(folder.getPath() + " not a folder.");
        }

        return load(FileUtils.getFiles(folder));
    }

    public List<Script<C>> load(Collection<File> files) {
        List<Script<C>> scripts = new ArrayList<>();

        for (File file : files) {
            if (file.getName().endsWith(".blox") || file.getName().endsWith(".json")) {
                Script<C> script = loadScript(file);
                scripts.add(script);
            }
        }

        return scripts;
    }

    @SuppressWarnings("unchecked")
    public Script<C> loadScript(File file) {
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

        Script<C> script = new Script<C>();

        for (Entry<String, Object> entry : (List<Entry<String, Object>>) data) {
            Object block = getBlock(entry.getKey(), entry.getValue());

            if (block instanceof ClientBlock) {
                script.addBlock((ClientBlock<C>) block);
            } else {
                script.addBlock(block);
            }
        }

        for (Block block : script.getBlocks()) {
            block.onLoad(script);
        }

        scripts.add(script);

        return script;
    }

    public void reloadScripts(String folder) {
        reloadScripts(new File(folder));
    }

    public void reloadScripts(File folder) {
        unloadScripts();
        load(folder);
    }

    public void unloadScripts() {
        for (Script<C> script : scripts) {
            unloadScript(script);
        }

        scripts.clear();
    }

    public void unloadScript(Script<C> script) {
        for (Block block : script.getBlocks()) {
            block.onUnload(script);
        }
    }

    private Object getBlock(String name, Object value) {
        Class<?> blockClass = blocks.get(name.toLowerCase().replaceAll(" ", ""));

        if (blockClass == null) {
            throw new IllegalArgumentException("Block '" + name + "' does not exist");
        }

        return getBlock(blockClass, value);
    }

    @SuppressWarnings("unchecked")
    private Object getBlock(Class<?> blockClass, Object value) {
        Constructor<?> constructor = null;
        List<Object> parameters = new ArrayList<>();

        Object primitiveValue = castValue(value);

        if (primitiveValue != null) {
            // value (string, number, or boolean), has format "Block": value,
            parameters.add(primitiveValue);
        } else if (value instanceof ArrayList) {
            // array, has format "Block": [ value1, value2, ... ],
            for (Object parameter : (List<Object>) value) {
                parameters.add(extractArgument(parameter, false));
            }
        } else if (value instanceof LinkedList) {
            // object, has format "Block": { "param1": value1, "param2": value2", ... },
            Map<String, Object> object = new HashMap<>();
            for (Entry<String, Object> keyValue : (List<Entry<String, Object>>) value) {
                // add parameter name-value pairs to map
                object.putIfAbsent(keyValue.getKey().toLowerCase(), extractArgument(keyValue.getValue(), true));
            }

            // search for suitable constructor
            constructors: for (Map.Entry<Constructor<?>, List<String>> entry : blockConstructors.get(blockClass).entrySet()) {
                // check if parameter names equal
                if (!object.keySet().equals(entry.getValue().stream().collect(Collectors.toSet()))) {
                    continue;
                }

                // clear parameters from previous attempts
                parameters.clear();

                // check if parameter types equal
                Class<?>[] paramTypes = entry.getKey().getParameterTypes();
                List<String> paramNames = entry.getValue();

                for (int i = 0; i < paramTypes.length; i++) {
                    String paramName = paramNames.get(i);
                    Object suppliedParam = object.get(paramName);
                    Class<?> suppliedClass = castClass(suppliedParam.getClass());

                    // nested block
                    if (suppliedParam instanceof LinkedList) {
                        suppliedParam = getBlock(paramTypes[i], suppliedParam);
                    } else {
                        // cast number to match type
                        suppliedParam = castNumber(paramTypes[i], suppliedParam);
                    }

                    // check parameter types equal
                    if (!suppliedClass.equals(paramTypes[i])) {
                        continue constructors;
                    }

                    // add parameter
                    parameters.add(suppliedParam);
                }

                // both parameter names and type equal, save the discovered constructor and stop searching
                constructor = entry.getKey();
                break;
            }

            // constructor not found
            if (constructor == null) {
                throw new IllegalArgumentException("Error loading " + blockClass.getSimpleName() + ", invalid parameters " + object);
            }
        }

        // if block has single value or array of values, search for constructor
        if (constructor == null) {
            // attempt to find valid constructor
            constructor = getConstructor(blockClass, parameters, false);

            // if not found, attempt to find valid constructor being more lenient
            if (constructor == null) {
                constructor = getConstructor(blockClass, parameters, true);
            }

            // throw exception if no constructor found
            if (constructor == null) {
                throw new IllegalArgumentException("Error loading " + blockClass.getSimpleName() + ", invalid parameters " + parameters);
            }

            // if last argument of constructor is an array, convert
            Class<?>[] paramTypes = constructor.getParameterTypes();
            if (paramTypes.length > 0 && paramTypes[paramTypes.length - 1].isArray()) {
                List<Object> typeArray = new ArrayList<>();

                // remove parameters that form an array
                if (parameters.size() >= paramTypes.length) {
                    for (int i = paramTypes.length - 1; i < parameters.size(); i++) {
                        typeArray.add(parameters.get(i));
                    }
                    for (int i = parameters.size() - 1; i >= paramTypes.length - 1; i--) {
                        parameters.remove(i);
                    }
                }

                // create array and add to the parameters
                Class<?> arrayTest = paramTypes[paramTypes.length - 1];

                if (arrayTest.getComponentType().isPrimitive()) {
                    parameters.add(createPrimitiveArray(typeArray, arrayTest));
                } else {
                    parameters.add(typeArray.toArray((Object[]) Array.newInstance(arrayTest.getComponentType(), typeArray.size())));
                }
            }
        }

        // initialize constructor
        try {
            return constructor.newInstance(parameters.toArray(new Object[parameters.size()]));
        } catch (Exception exception) {
            throw new IllegalArgumentException("Error loading " + blockClass.getSimpleName() + ", invalid parameters " + parameters);
        }
    }

    private Constructor<?> getConstructor(Class<?> clazz, List<Object> parameters, boolean lenient) {
        search: for (Constructor<?> c : clazz.getConstructors()) {
            int numParameters = c.getParameterTypes().length;

            // don't require array parameter
            if (numParameters > 0 && c.getParameterTypes()[c.getParameterTypes().length - 1].isArray()) {
                numParameters--;
            }

            // continue if not enough parameters
            if (parameters.size() < numParameters) {
                continue;
            }

            // check parameter types equal
            for (int i = 0; i < Math.min(c.getParameterTypes().length, parameters.size()); i++) {
                Class<?> requiredParameter = c.getParameterTypes()[i];

                // fix parameter if it is an array
                boolean array = requiredParameter.isArray() && i == c.getParameterTypes().length - 1;
                requiredParameter = array ? requiredParameter.getComponentType() : requiredParameter;

                // if array, check all remaining parameters equal the array type
                for (int j = i; j < (array ? parameters.size() : i + 1); j++) {
                    Class<?> suppliedParameter = castClass(parameters.get(j).getClass());

                    if (!suppliedParameter.equals(requiredParameter)) {

                        // if lenient, try number casting to make parameter types match
                        if (lenient) {
                            Object number = castNumber(requiredParameter, parameters.get(j));
                            suppliedParameter = castClass(number.getClass());

                            if (suppliedParameter.equals(requiredParameter)) {
                                // update parameters with casted number
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

    private Object extractArgument(Object argument, boolean lenient) {
        Object argumentValue = castValue(argument);

        if (argumentValue != null) {
            return argumentValue;
        } else if (argument instanceof ArrayList) {
            @SuppressWarnings("unchecked")
            List<Object> list = (List<Object>) argument;
            for (int i = 0; i < list.size(); i++) {
                list.set(i, extractArgument(list.get(i), false));
            }
            return list;
        }

        if (!lenient) {
            throw new IllegalArgumentException("Invalid argument " + argument + " (" + argument.getClass() + ")");
        } else {
            return argument;
        }
    }

    private Object castValue(Object value) {
        if (value instanceof String || value instanceof Integer || value instanceof Double || value instanceof Boolean) {
            return value;
        } else if (value instanceof BigInteger) {
            return ((BigInteger) value).intValue();
        } else if (value instanceof BigDecimal) {
            return ((BigDecimal) value).doubleValue();
        }
        return null;
    }

    private Object castNumber(Class<?> requiredParameter, Object parameter) {
        Class<?> suppliedParameter = parameter.getClass();

        // casts numbers to the specified type
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

    private Class<?> castClass(Class<?> clazz) {
        return paramCasts.getOrDefault(clazz, clazz);
    }

    private List<String> getConstructorParameterNames(Constructor<?> constructor) {
        Class<?> declaringClass = constructor.getDeclaringClass();
        ClassLoader declaringClassLoader = declaringClass.getClassLoader();

        Type declaringType = Type.getType(declaringClass);
        String constructorDescriptor = Type.getConstructorDescriptor(constructor);
        String url = declaringType.getInternalName() + ".class";

        InputStream classFileInputStream = declaringClassLoader.getResourceAsStream(url);
        if (classFileInputStream == null) {
            throw new IllegalArgumentException("The constructor's class loader cannot find the bytecode that defined the constructor's class (URL: " + url + ")");
        }

        ClassNode classNode = null;
        try {
            try {
                classNode = new ClassNode();
                ClassReader classReader = new ClassReader(classFileInputStream);
                classReader.accept(classNode, 0);
            } finally {
                classFileInputStream.close();
            }
        } catch (Exception exception) {
            throw new IllegalArgumentException("Error getting constructor parameter names for " + constructor.getClass());
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

    public void addBlocks(String packageName) {
        ClassUtils.getClasses(packageName).forEach(this::addBlock);
    }

    public void addBlock(Class<?> blockType) {
        blocks.put(blockType.getSimpleName().toLowerCase(), blockType);

        Map<Constructor<?>, List<String>> constructors = new HashMap<>();
        for (Constructor<?> constructor : blockType.getConstructors()) {
            List<String> parameterNames = getConstructorParameterNames(constructor).stream().map(String::toLowerCase).collect(Collectors.toList());
            constructors.put(constructor, parameterNames);
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
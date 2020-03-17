package com.tomallton.blox.util;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

public class ClassUtils {

    public static Set<Class<?>> getClasses(String packageName) {
        File jarFile = null;
        try {
            jarFile = new File(ClassUtils.class.getProtectionDomain().getCodeSource().getLocation().toURI());
        } catch (URISyntaxException exception) {
            exception.printStackTrace();
        }

        if (jarFile.isFile()) {
            return getClassesFromJar(jarFile, packageName);
        } else {
            return getClassesFromClassLoader(packageName);
        }
    }

    private static Set<Class<?>> getClassesFromClassLoader(String packageName) {
        List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
        classLoadersList.add(ClasspathHelper.contextClassLoader());
        classLoadersList.add(ClasspathHelper.staticClassLoader());

        ConfigurationBuilder config = new ConfigurationBuilder();
        // don't exclude Object.class
        config.setScanners(new SubTypesScanner(false), new ResourcesScanner());
        // filter package
        config.filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix(packageName)));
        config.setUrls(ClasspathHelper.forJavaClassPath());

        return new Reflections(config).getSubTypesOf(Object.class);
    }

    private static Set<Class<?>> getClassesFromJar(File jarFile, String packageName) {
        Set<Class<?>> classes = new HashSet<Class<?>>();
        try {
            JarFile file = new JarFile(jarFile);
            for (Enumeration<JarEntry> entry = file.entries(); entry.hasMoreElements();) {
                JarEntry jarEntry = entry.nextElement();
                String name = jarEntry.getName().replace("/", ".");
                if (name.startsWith(packageName) && name.endsWith(".class")) {
                    classes.add(Class.forName(name.substring(0, name.length() - ".class".length())));
                }
            }
            file.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return classes;
    }

}
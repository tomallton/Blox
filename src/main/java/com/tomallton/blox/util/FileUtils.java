package com.tomallton.blox.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 */
public class FileUtils {

    public static Set<File> getFiles(File folder) {
        Set<File> files = new HashSet<>();

        if (!folder.isDirectory()) {
            return files;
        }

        for (File file : folder.listFiles()) {
            if (file.isDirectory()) {
                files.addAll(getFiles(file));
            } else {
                files.add(file);
            }
        }

        return files;
    }

    public static List<String> readFile(File file) {
        return readFile(file.getPath());
    }

    public static List<String> readFile(String fileName) {
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            return stream.collect(Collectors.toList());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
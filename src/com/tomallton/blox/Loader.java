package com.tomallton.blox;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.tomallton.blox.parser.Entry;
import com.tomallton.blox.parser.Parser;
import com.tomallton.blox.util.FileUtils;

public class Loader {

    private File folder;

    private final Map<String, Class<? extends Block>> blockTypes = new HashMap<>();
    
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
            System.out.println(file.getName() + " does not contain a blox object");
            return;
        }

        List<Entry<String, Object>> objects = (LinkedList<Entry<String, Object>>) data;

        for (Entry<String, Object> entry : objects) {
            Class<?> blockClass = null;
            

//            for (String packageName : packages) {
//                try {
//                    blockClass = Class.forName(packageName + "." + entry.getKey());
//                    break;
//                } catch (Exception ignored) {
//                }
//            }
        }
    }

    public void setFolder(File folder) {
        this.folder = folder;
    }
    
    public void addBlockType(String packageName) {
//        for (Class<?> blockType : new Reflection)
    }

    public void addBlockType(Class<? extends Block> blockType) {
        blockTypes.put(blockType.getSimpleName().toLowerCase(), blockType);
    }
    
}
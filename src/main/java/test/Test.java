package test;

import java.io.File;

import com.tomallton.blox.Loader;

public class Test {

    public static void main(String[] args) throws Exception {
        System.out.println("starting classes..");

        // for (String clazz : new Reflections("test.blocks").getAllTypes()) {
        // System.out.println(clazz);
        // }

        Loader<ConsoleClient> loader = new Loader<>();
        loader.addBlockType("test.blocks");
        loader.setFolder(new File("/Users/Tom/Downloads/"));
        loader.load();
    }

}
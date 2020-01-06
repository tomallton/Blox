package test;

import java.io.File;

import com.tomallton.blox.Loader;

public class Test {

    public static void main(String[] args) throws Exception {
        Loader<ConsoleClient> loader = new Loader<>();
        loader.addBlockType("test.blocks");
        loader.load(new File("/Users/Tom/Downloads/"));
    }

}
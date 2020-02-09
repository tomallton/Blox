package test;

import java.io.File;

import com.tomallton.blox.Blox;

/**
 * TODO:
 * - Create GUI
 * - Fully comment all methods with JavaDocs
 */
public class Test {

    public static void main(String[] args) throws Exception {
        Blox<ConsoleClient> loader = new Blox<>();
        loader.addBlocks("test.blocks");
        loader.load(new File("/Users/Tom/Documents/GitHub/Blox/src/main/resources"));
    }

}
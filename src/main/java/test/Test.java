package test;

import java.io.File;

import com.tomallton.blox.Loader;

/**
 * TODO:
 * - Support nested stages
 * - Support number conversions, lists, in object stages
 * - Handle errors better
 * - Create GUI
 */
public class Test {

    public static void main(String[] args) throws Exception {
        Loader<ConsoleClient> loader = new Loader<>();
        loader.addBlockType("test.blocks");
        loader.load(new File("/Users/Tom/Documents/GitHub/Blox/src/main/resources"));
    }

}
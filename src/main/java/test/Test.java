package test;

import java.io.File;

import com.tomallton.blox.Blox;

/**
 * TODO:
 * - Support nested stages
 * - Support number conversions and lists in object stages
 * - Handle errors better
 * - Create GUI
 */
public class Test {

    public static void main(String[] args) throws Exception {
        Blox<ConsoleClient> loader = new Blox<>();
        loader.addBlockType("test.blocks");
        loader.load(new File("/Users/Tom/Documents/GitHub/Blox/src/main/resources"));
    }

}
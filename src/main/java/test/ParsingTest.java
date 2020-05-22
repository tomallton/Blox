package test;

import com.tomallton.blox.Blox;

/**
 * TODO:
 * - Create GUI
 * - Fully comment all methods with JavaDocs
 */
public class ParsingTest {

    public static void main(String[] args) throws Exception {
        Blox<ConsoleClient> blox = new Blox<>();
        blox.addBlocks("test.blocks");
        blox.load("src/main/resources");
    }

}
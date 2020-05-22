package test;

import java.io.File;

import com.tomallton.blox.Blox;
import com.tomallton.blox.Script;

public class APITest {

    public static void main(String[] args) {
        Blox<ConsoleClient> blox = new Blox<>();
        blox.addBlocks("test.blocks");

        // load program
        Script<ConsoleClient> script = blox.load(new File("src/main/resources")).get(0);
        
        // make client 'run' program
        script.enter(new ConsoleClient());

        // unload program
        blox.unloadScript(script);
    }

}
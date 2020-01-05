package com.tomallton.blox;

import java.io.File;

public class Test {

    public static void main(String[] args) throws Exception {
        System.out.println("starting");

        Loader loader = new Loader();
        loader.addBlockType(Foo.class);
        loader.setFolder(new File("/Users/Tom/Downloads/"));
        loader.load();
    }

}
package com.tomallton.blox;

import java.util.LinkedList;
import java.util.List;

public class Program {

    private final List<Block> blocks = new LinkedList<>();

    public void addBlock(Block block) {
        blocks.add(block);
    }

}
package com.tomallton.blox;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Program {

    private final List<Block> blocks = new LinkedList<>();

    private final Map<Class<? extends Block>, Set<Block>> typeToBlock = new HashMap<>();

    public void addBlock(Block block) {
        blocks.add(block);

        typeToBlock.putIfAbsent(block.getClass(), new HashSet<>());
        typeToBlock.get(block.getClass()).add(block);
    }
}
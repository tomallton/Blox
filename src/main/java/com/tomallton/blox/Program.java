package com.tomallton.blox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Program<C> {
    private final Map<Class<?>, Set<Object>> typeToBlock = new HashMap<>();

    private final List<ClientBlock<C>> blocks = new ArrayList<>();

    public void addBlock(ClientBlock<C> block) {
        addBlock((Object) block);

        blocks.add(block);
    }

    public void addBlock(Object block) {
        typeToBlock.putIfAbsent(block.getClass(), new HashSet<>());
        typeToBlock.get(block.getClass()).add(block);
    }

    public <B> B getBlock(Class<B> blockType) {
        Set<B> blocks = getBlocks(blockType);

        return blocks.isEmpty() ? null : blocks.iterator().next();
    }

    @SuppressWarnings("unchecked")
    public <B> Set<B> getBlocks(Class<B> blockType) {
        return (Set<B>) typeToBlock.get(blockType);
    }

    public void run(C client) {

    }

}
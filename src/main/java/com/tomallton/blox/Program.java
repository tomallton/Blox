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
        block.setProgram(this);
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

    public void enter(C client) {
        exit(client);

        if (!blocks.isEmpty()) {
            if (blocks.get(0).add(client)) {
                blocks.get(0).onEnterProgram(client);
            }
        }
    }

    public void exit(C client) {
        for (ClientBlock<C> block : blocks) {
            if (block.remove(client)) {
                block.onExitProgram(client);
            }
        }
    }

    public void progress(C client) {
        for (int i = 0; i < blocks.size(); i++) {
            if (blocks.get(i).remove(client)) {
                if (i + 1 < blocks.size()) {
                    blocks.get(i + 1).add(client);
                } else {
                    blocks.get(i).onExitProgram(client);
                }
                return;
            }
        }
        enter(client);
    }

    public boolean has(C client) {
        for (ClientBlock<C> block : blocks) {
            if (block.has(client)) {
                return true;
            }
        }
        return false;
    }

}
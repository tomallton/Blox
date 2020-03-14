package com.tomallton.blox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Script<C> {

    private final List<Block> blocks = new ArrayList<>();

    private final List<ClientBlock<C>> clientBlocks = new ArrayList<>();

    private final Map<Class<?>, Set<Object>> typeToBlock = new HashMap<>();

    private final Map<String, Object> attributes = new HashMap<>();

    public void addBlock(ClientBlock<C> block) {
        addBlock((Object) block);

        clientBlocks.add(block);
        block.setScript(this);
    }

    public void addBlock(Object block) {
        if (block instanceof Block) {
            blocks.add((Block) block);
        }

        typeToBlock.computeIfAbsent(block.getClass(), c -> new HashSet<>()).add(block);
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public List<ClientBlock<C>> getClientBlocks() {
        return clientBlocks;
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
        remove(client);

        if (!clientBlocks.isEmpty()) {
            clientBlocks.get(0).add(client);
        }
    }

    public void remove(C client) {
        for (ClientBlock<C> block : clientBlocks) {
            block.remove(client);
        }
    }

    public boolean has(C client) {
        for (ClientBlock<C> block : clientBlocks) {
            if (block.has(client)) {
                return true;
            }
        }
        return false;
    }

    public Object getAttribute(String key) {
        return attributes.get(key);
    }

    public void setAttribute(String key, Object value) {
        attributes.put(key, value);
    }
}
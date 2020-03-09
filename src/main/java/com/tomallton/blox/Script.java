package com.tomallton.blox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Script<C> {
    private final Map<Class<?>, Set<Object>> typeToBlock = new HashMap<>();

    private final List<ClientBlock<C>> clientBlocks = new ArrayList<>();

    public void addBlock(ClientBlock<C> block) {
        addBlock((Object) block);

        clientBlocks.add(block);
        block.setScript(this);
    }

    public void addBlock(Object block) {
        typeToBlock.computeIfAbsent(block.getClass(), c -> new HashSet<>()).add(block);
    }

    public <B> B getBlock(Class<B> blockType) {
        Set<B> blocks = getBlocks(blockType);

        return blocks.isEmpty() ? null : blocks.iterator().next();
    }

    @SuppressWarnings("unchecked")
    public <B> Set<B> getBlocks(Class<B> blockType) {
        return (Set<B>) typeToBlock.get(blockType);
    }

    public List<ClientBlock<C>> getClientBlocks() {
        return clientBlocks;
    }

    public void enter(C client) {
        exit(client);

        if (!clientBlocks.isEmpty()) {
            clientBlocks.get(0).add(client);
        }
    }

    public void exit(C client) {
        for (ClientBlock<C> block : clientBlocks) {
            block.remove(client);
        }
    }

    public void progress(C client) {
        for (int i = 0; i < clientBlocks.size(); i++) {
            if (clientBlocks.get(i).remove(client)) {
                if (i + 1 < clientBlocks.size()) {
                    clientBlocks.get(i + 1).add(client);
                } else {
                    clientBlocks.get(i).onExitScript(client);
                }
                return;
            }
        }

        enter(client);
    }

    public boolean has(C client) {
        for (ClientBlock<C> block : clientBlocks) {
            if (block.has(client)) {
                return true;
            }
        }
        return false;
    }
}
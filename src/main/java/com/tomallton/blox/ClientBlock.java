package com.tomallton.blox;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class ClientBlock<C> implements Block, Consumer<C> {
    private Script<C> script;
    private int index;

    private final Set<C> clients = new HashSet<>();

    public void onEnter(C client) {
    }

    public void onEnterScript(C client) {
    }

    public void onExit(C client) {
    }

    public void onExitScript(C client) {
    }

    public void progress(C client) {
        if (script != null) {
            ClientBlock<C> next = getNextBlock();
            if (next != null) {
                remove(client);
                next.add(client);
                return;
            }
        }

        onExitScript(client);
        remove(client);
    }

    public Set<C> getClients() {
        return clients;
    }

    public boolean has(C client) {
        return clients.contains(client);
    }

    @Override
    public void accept(C client) {
        add(client);
    }

    public boolean add(C client) {
        boolean inScript = script.has(client);

        if (clients.add(client)) {
            onEnter(client);
            if (!inScript) {
                for (ClientBlock<C> block : script.getClientBlocks()) {
                    block.onEnterScript(client);
                }
            }
            return true;
        }
        return false;
    }

    public boolean remove(C client) {
        if (clients.remove(client)) {
            onExit(client);
            return true;
        }
        return false;
    }

    public int getIndex() {
        return index;
    }

    public ClientBlock<C> getNextBlock() {
        return index + 1 < script.getClientBlocks().size() ? script.getClientBlocks().get(index + 1) : null;
    }

    protected void setScript(Script<C> script) {
        this.script = script;
        this.index = script.getClientBlocks().indexOf(this);
    }
}
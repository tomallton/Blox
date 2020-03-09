package com.tomallton.blox;

import java.util.HashSet;
import java.util.Set;

public class ClientBlock<C> {
    private Script<C> script;
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
            script.progress(client);
        } else {
            remove(client);
            onExitScript(client);
        }
    }

    public Set<C> getClients() {
        return clients;
    }

    public boolean has(C client) {
        return clients.contains(client);
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

    protected void setScript(Script<C> script) {
        this.script = script;
    }
}
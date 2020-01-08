package com.tomallton.blox;

import java.util.HashSet;
import java.util.Set;

public class ClientBlock<C> {
    private Program<C> program;
    private final Set<C> clients = new HashSet<>();

    public void onEnter(C client) {
    }

    public void onEnterProgram(C client) {
    }

    public void onExit(C client) {
    }

    public void onExitProgram(C client) {
    }

    public void progress(C client) {
        if (program != null) {
            program.progress(client);
        } else {
            remove(client);
        }
    }

    public Set<C> getClients() {
        return clients;
    }

    public boolean has(C client) {
        return clients.contains(client);
    }

    public boolean add(C client) {
        if (clients.add(client)) {
            onEnter(client);
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
}
package com.tomallton.blox;

import java.util.HashSet;
import java.util.Set;

public class ClientBlock<C> {
    private final Set<C> clients = new HashSet<>();

    public void onEnter(C client) {
    }

    public void onEnterProgram(C client) {
    }

    public void onExit(C client) {
    }

    public void onExitProgram(C client) {
    }

    public Set<C> getClients() {
        return clients;
    }

    public boolean hasClient(C client) {
        return clients.contains(client);
    }
}
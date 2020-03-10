package com.tomallton.blox.blocks;

import com.tomallton.blox.Block;
import com.tomallton.blox.Script;

public class Name implements Block {
    private final String name;

    public Name(String name) {
        this.name = name;
    }

    @Override
    public void onLoad(Script<?> script) {
        script.setAttribute("name", name);
    }
}
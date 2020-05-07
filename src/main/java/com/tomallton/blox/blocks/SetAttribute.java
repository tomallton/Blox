package com.tomallton.blox.blocks;

import com.tomallton.blox.Block;
import com.tomallton.blox.Script;

public class SetAttribute implements Block {
    private final String key;
    private final Object value;

    public SetAttribute(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public void onLoad(Script<?> script) {
        script.setAttribute(key, value);
    }
}
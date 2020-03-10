package com.tomallton.blox;

public interface Block {

    default void onLoad(Script<?> script) {
    }

    default void onUnload(Script<?> script) {
    }

}
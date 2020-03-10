package com.tomallton.blox.blocks;

import com.tomallton.blox.ClientBlock;

public class Wait<C> extends ClientBlock<C> {
    private final long timeMilliseconds;

    public Wait(long timeMilliseconds) {
        this.timeMilliseconds = timeMilliseconds;
    }

    @Override
    public void onEnter(C client) {
        try {
            Thread.sleep(timeMilliseconds);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

        progress(client);
    }
}
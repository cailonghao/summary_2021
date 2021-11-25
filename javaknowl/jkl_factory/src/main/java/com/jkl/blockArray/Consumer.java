package com.jkl.blockArray;

public class Consumer {

    public void remove() throws InterruptedException {
        String msg = BlockArray.blockingQueue.take();
        System.out.println("msg = " + msg);
    }
}

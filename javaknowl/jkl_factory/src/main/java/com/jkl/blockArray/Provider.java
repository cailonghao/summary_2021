package com.jkl.blockArray;

public class Provider {


    public void add(String msg){
        BlockArray.blockingQueue.add(msg);
    }


}

package com.jkl.prototype;

public class Client {

    private Proto proto;

    public Client(Proto proto) {
        this.proto = proto;
    }

    public Proto startClone() {
        return proto.clone();
    }
}

package com.jkl.factory.simple;

public class OrderFactory {

    public IOrder create(String name) {
        if ("normal".equals(name)) {
            return new NormalOrder();
        }
        return null;
    }
}

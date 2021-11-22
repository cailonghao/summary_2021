package com.jkl.factory.simple;


public class NormalOrder implements IOrder{
    public void createOrder(String name) {
        System.out.println("normal order...");
    }
}

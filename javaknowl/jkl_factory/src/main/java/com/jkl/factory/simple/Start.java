package com.jkl.factory.simple;

/**
 * 简单工厂
 */
public class Start {

    public static void main(String[] args) {
        OrderFactory factory = new OrderFactory();
        NormalOrder normalOrder = (NormalOrder) factory.create("normal");
        normalOrder.createOrder("java");
    }
}

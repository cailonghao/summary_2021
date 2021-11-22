package com.jkl.factory.facMethod;

public abstract class CookieFactpry {

    public void preCreate() {
        System.out.println("工厂类");
    }

    public abstract void create();
}

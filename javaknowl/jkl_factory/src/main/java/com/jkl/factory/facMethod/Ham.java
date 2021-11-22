package com.jkl.factory.facMethod;

public class Ham extends CookieFactpry {
    public void create() {
        super.preCreate();
        System.out.println("生产火腿");
    }
}

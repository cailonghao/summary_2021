package com.jkl.factory.abstractFac;

public class AppleFactory implements EleAbsFavtory {
    public void createPc() {
        System.out.println("创造了一台 apple PC");
    }

    public void createPhone() {
        System.out.println("创造了一台 apple PHONE");
    }

    public void createWatch() {
        System.out.println("创造了一台 apple WATCH");
    }
}

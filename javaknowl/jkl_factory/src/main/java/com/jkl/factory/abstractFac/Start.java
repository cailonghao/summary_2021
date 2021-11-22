package com.jkl.factory.abstractFac;

/**
 * 抽象工厂
 */
public class Start {

    public static void main(String[] args) {
        EleAbsFavtory eleAbsFavtory = new AppleFactory();
        eleAbsFavtory.createPc();
        EleAbsFavtory e1 = new GoogleFavtory();
        e1.createPhone();
    }
}

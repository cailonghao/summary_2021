package com.jkl.proxy.staticProxy;

public class Start {

    public static void main(String[] args) {
        Fateher fateher = new Fateher(new Son());
        fateher.findLove();
    }
}

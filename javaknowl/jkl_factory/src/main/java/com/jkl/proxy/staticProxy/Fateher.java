package com.jkl.proxy.staticProxy;

public class Fateher {
    private Son son;

    public Fateher(Son son) {
        this.son = son;
    }

    public void findLove() {
        System.out.println("findLove");
        son.findLove();
        System.out.println("son findLove");
    }

}

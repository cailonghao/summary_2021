package com.jkl.factory.abstractFac;

public class GoogleFavtory implements EleAbsFavtory {
    public void createPc() {
        System.out.println("创造了一台 google PC");
    }

    public void createPhone() {
        System.out.println("创造了一台 google PHONE");
    }

    public void createWatch() {
        System.out.println("创造了一台 google WATCH");
    }
}

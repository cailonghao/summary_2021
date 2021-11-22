package com.jkl.singleton.singleton1;

public class Start {

    public static void main(String[] args) {
        for (int i=0;i <10;i++){
            new Thread(new Runnable() {
                public void run() {
                    //System.out.println(Single1.getInstance());
                    System.out.println(Single2.getInstance());
                }
            }).start();
        }
    }
}

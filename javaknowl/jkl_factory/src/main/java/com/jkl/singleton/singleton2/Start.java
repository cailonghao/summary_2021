package com.jkl.singleton.singleton2;

import com.jkl.singleton.singleton1.Single2;

public class Start {

    public static void main(String[] args) {
        for (int i=0;i <10;i++){
            new Thread(new Runnable() {
                public void run() {
                    //System.out.println(Single1.getInstance());
                    System.out.println(Single3.getInstance());
                }
            }).start();
        }
    }
}

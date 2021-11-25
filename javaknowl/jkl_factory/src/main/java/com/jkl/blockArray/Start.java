package com.jkl.blockArray;

public class Start {

    public static void main(String[] args) throws InterruptedException {
        Provider provider = new Provider();

        new Thread(new Runnable() {
            public void run() {
                Consumer consumer = new Consumer();
                try {
                    Thread.sleep(1000);
                    while (true){
                        consumer.remove();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        for (int i = 0; i < 100; i++) {
            Thread.sleep(1000);
            provider.add(" message +" + i);
            System.out.println(BlockArray.blockingQueue.size());
        }
    }
}

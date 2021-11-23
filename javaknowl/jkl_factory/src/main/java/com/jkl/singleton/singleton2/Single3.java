package com.jkl.singleton.singleton2;

public class Single3 {
    private Single3() {
        if (LazyHoder.LAZY != null) {
            throw new RuntimeException("非法创建实例");
        }
    }

    public static final Single3 getInstance() {
        return LazyHoder.LAZY;
    }

    private static class LazyHoder {
        private static final Single3 LAZY = new Single3();
    }

}

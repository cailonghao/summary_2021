package com.jkl.singleton.singleton3;

/**
 * 注册式单例  枚举等级 单个实例
 */
public enum Single4 {


    INSTANTCE;

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static Single4 getInstance() {
        return INSTANTCE;
    }
}

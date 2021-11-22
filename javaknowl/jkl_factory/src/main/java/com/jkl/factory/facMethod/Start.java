package com.jkl.factory.facMethod;

/**
 * 简单工厂需要修改判断条件
 * 工厂方法模式
 *  符合开闭原则 新增只需要添加对应的子类
 */
public class Start {

    public static void main(String[] args) {
        CookieFactpry cookieFactpry = new Ham();
        cookieFactpry.create();
    }
}

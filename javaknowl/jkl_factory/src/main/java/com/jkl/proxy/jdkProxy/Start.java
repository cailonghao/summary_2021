package com.jkl.proxy.jdkProxy;

import com.jkl.proxy.staticProxy.Person;
import com.jkl.proxy.staticProxy.Son;

public class Start {

    public static void main(String[] args) {
        Person person = new Son();
        Person proxy = (Person) new ProxyFactory(person).getProxyInstance();
        proxy.findLove();
    }
}

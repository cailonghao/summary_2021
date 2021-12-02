package com.dubbo.provider.service;

import com.dubbo.api.Dubbo1Service;
import org.apache.dubbo.config.annotation.DubboService;


@DubboService(version = "1.0.0")
public class Dubbo1ServiceImpl implements Dubbo1Service {

    @Override
    public String sayHello(String name) {
        System.out.println("hrllo");
        return "你好 " + name;
    }


}

package com.dubbo.dub2.service;

import com.dubbo.api.Dubbo1Service;
import com.dubbo.api.Dubbo2Service;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Value;

@DubboService(version = "1.0.0")
public class Dubbo2ServiceImpl implements Dubbo2Service {


    @DubboReference(version = "1.0.0")
    private Dubbo1Service dubbo1Service;
    @Value("${spring.application.name}")
    private String appName;

    @Override
    public void doSomething(String name) {
        System.out.println("appName = " + appName);
        System.out.println("dub2 = " + name);
        dubbo1Service.sayHello(name);
    }
}

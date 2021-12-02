package com.dubbo.dub2.controller;

import com.dubbo.api.Dubbo1Service;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/index")
public class IndexController {

    @DubboReference(version = "1.0.0")
    private Dubbo1Service dubbo1Service;

    @RequestMapping("/init")
    public void init() {
        System.out.println(dubbo1Service.sayHello("大哥"));
    }
}

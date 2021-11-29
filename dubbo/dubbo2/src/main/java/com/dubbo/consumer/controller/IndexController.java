package com.dubbo.consumer.controller;

import com.dubbo.api.ApiService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/index")
public class IndexController {

    @DubboReference(version = "1.0.0")
    private ApiService apiService;

    @PostConstruct
    public void init() {
        System.out.println(apiService.sayHello("大哥"));
    }
}

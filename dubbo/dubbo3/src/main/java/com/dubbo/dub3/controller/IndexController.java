package com.dubbo.dub3.controller;

import com.dubbo.api.Dubbo2Service;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/index")
public class IndexController {

    @DubboReference(version = "1.0.0")
    private Dubbo2Service dubbo2Service;

    @RequestMapping("/init")
    public void init() {
        dubbo2Service.doSomething("大哥");
    }
}

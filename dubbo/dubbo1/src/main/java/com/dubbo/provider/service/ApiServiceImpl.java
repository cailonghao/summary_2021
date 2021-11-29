package com.dubbo.provider.service;

import com.dubbo.api.ApiService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

@DubboService(version = "1.0.0")
public class ApiServiceImpl implements ApiService {

    @Override
    public String sayHello(String name) {
        return "你好 " + name;
    }
}

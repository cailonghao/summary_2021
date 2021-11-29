package com.cloud.lx.service;

import com.cloud.lx.aop.RedisAop;
import com.cloud.lx.constant.RedisConstant;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

@Service
public class YewuService {


    @RedisAop(key = "key",module = RedisConstant.default_module)
    public String saveCache(String key, String object) {
        System.out.println("key = " + key);
        System.out.println("object = " + object);
        object += "+method xxx";
        System.out.println(object);
        return object;
    }



    @RedisAop(key = "key", module = RedisConstant.default_module, mode = 3)
    public String getCache(String key) {
        System.out.println("cache key = " + key);
        return "hahhahhah";
    }
}

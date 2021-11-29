package com.cloud.lx.controller;

import com.cloud.lx.service.RedisService;
import com.cloud.lx.service.YewuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("redis")
public class RedisController {

    @Autowired
    YewuService yewuService;



    @RequestMapping("/setRedis")
    public void setRedis(String key, String value) {
        yewuService.saveCache(key, value);
    }


    @RequestMapping("/getRedis")
    public Object getRedis(String key) {
        return yewuService.getCache(key);
    }



}

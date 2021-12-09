package com.im.server.controller;

import com.im.server.config.RedisConfig;
import com.im.server.constant.RedisConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/index")
public class IndexController {


    @RequestMapping("/getUuid")
    public String getUuid(@RequestParam String username,
                          @RequestParam String password) {
        log.info("username = {} ,password = {}", username, password);
        Jedis jedis = RedisConfig.jedisPool.getResource();
        String uuid = UUID.randomUUID().toString();
        jedis.sadd(RedisConstant.imLoginSet, uuid);
        return uuid;
    }
}

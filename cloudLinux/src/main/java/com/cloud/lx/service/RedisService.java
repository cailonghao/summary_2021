package com.cloud.lx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    public Object getString(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void setString(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void delString(String key) {
        redisTemplate.delete(key);
    }
}

package com.im.server.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPubSub;

@Slf4j
@Service
public class JedisPubsub extends JedisPubSub {

    @Override
    public void onMessage(String channel, String message) {
        log.info("订阅消息 ---> {}", message);
    }
}

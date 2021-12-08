package com.im.client.config;


import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.JedisPool;

public class RedisConfig {

    private static JedisPool jedisPool;

    public void init() {
        try {
            GenericObjectPoolConfig config = new GenericObjectPoolConfig();
            jedisPool = new JedisPool(config, "1.13.253.101", 6379, 5000, "123456");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedisPool != null) {
                jedisPool.close();
            }
        }
    }

}

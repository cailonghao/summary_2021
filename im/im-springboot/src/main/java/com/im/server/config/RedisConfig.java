package com.im.server.config;


import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Configuration
public class RedisConfig {

    public static JedisPool jedisPool;

    static {
        GenericObjectPoolConfig<Jedis> config = new GenericObjectPoolConfig<>();
        config.setMaxTotal(5);
        config.setMaxIdle(5);
        config.setMinIdle(3);
        config.setJmxEnabled(true);
        config.setMaxWaitMillis(5000);
        jedisPool = new JedisPool(config, "1.13.253.101", 6379, 5000, "123456");
    }

    public static Jedis getJedis() {

        return jedisPool.getResource();
    }

    public static void returnResource(Jedis jedis) {
        if (jedis != null) {
            jedisPool.returnResource(jedis);
        }
    }


}

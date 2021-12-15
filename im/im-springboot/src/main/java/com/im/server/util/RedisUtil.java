package com.im.server.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.im.server.config.RedisConfig;
import com.im.server.constant.RedisConstant;
import com.im.server.dto.ChatDto;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

public class RedisUtil {

    private static JedisPool pool = RedisConfig.jedisPool;


    public static void cacheUserLogin(String uuid) throws JsonProcessingException {
        Jedis jedis = pool.getResource();
        jedis.sadd(RedisConstant.imLoginSet, uuid);
        pool.returnResource(jedis);
    }


    public static List<String> getChatroom(String userId, String accept, Integer curPage) {
        Integer start = curPage*10;
        Integer end = start+15;
        Jedis jedis = pool.getResource();
        List<String> list = jedis.lrange(userId + "@" + accept, start, end);
        pool.returnResource(jedis);
        return list;
    }

    public static void cacheChatroom(String userId, String accept, String json) {
        Jedis jedis = pool.getResource();
        jedis.lpush(userId + "@" + accept, json);
        pool.returnResource(jedis);
    }
}

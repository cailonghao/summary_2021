package com.dubbo.provider.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

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

    /**
     * 发布消息
     *
     * @param msg
     */
    public void sendTopMessage(String msg) {
        redisTemplate.convertAndSend("topica", msg);
    }

    public void addChannel(String user, String id) {
        redisTemplate.opsForValue().set("nt:channel" + user, id);
    }

    public void delChannel(String user) {
        redisTemplate.delete("nt:channel" + user);
    }

    public String getChannel(String user) {
        return (String) redisTemplate.opsForValue().get("nt:channel" + user);
    }

    /**
     * 验证登录
     *
     * @param name
     * @return
     */
    public String addUser(String name) {
        String uuid = UUID.randomUUID().toString();
        redisTemplate.opsForSet().add("imuser", uuid);
        return uuid;
    }

    /**
     * 验证登录
     *
     * @param name
     * @return
     */
    public Boolean hasUser(String name) {
        return redisTemplate.opsForSet().isMember("imuser", name);
    }

    /**
     * 用户关联房间
     */
    public void addSendLineAccept(String send, String accept) {
        redisTemplate.opsForSet().add("sl:" + send, accept);
    }

    public Boolean getFangjian(String userId, String accept) {
        return redisTemplate.opsForSet().isMember("sl:" + userId, accept);
    }

    /**
     * 房间添加消息
     */
    public void addFangjianMsg(String send, String accept, String msg) {
        redisTemplate.opsForList().leftPush(paixu(send, accept), msg);
    }

    public List<Object> getFangjianMsg(String send, String accept) {
        List<Object> list = redisTemplate.opsForList().range(paixu(send, accept), 0, -1);
        return list;
    }

    public String paixu(String send, String accept) {
        String[] stringArray = {send, accept,};
        StringBuilder ss = new StringBuilder();
        Arrays.sort(stringArray, String::compareToIgnoreCase);
        for (String sss : stringArray) {
            ss.append(sss);
        }
        return ss.toString();
    }


}

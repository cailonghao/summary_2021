package com.im.server.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.im.server.config.RedisConfig;
import com.im.server.constant.RedisConstant;
import com.im.server.dto.ChatDto;
import com.im.server.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/index")
public class IndexController {

    @Value("${server.port}")
    private Integer port;

    @RequestMapping("/getUuid")
    public ChatDto getUuid(@RequestParam String username,
                           @RequestParam String password) throws JsonProcessingException {
        log.info("username = {} ,password = {}", username, password);
        String hash = String.valueOf((username + password + port).hashCode());
        ChatDto dto = new ChatDto(hash,username);
        ObjectMapper mapper = new ObjectMapper();
        RedisUtil.cacheUserLogin(hash);
        return dto;
    }

    @RequestMapping("/getList")
    public List<ChatDto> getList() {
        Jedis jedis = RedisConfig.getJedis();
        Set<String> list = jedis.smembers(RedisConstant.imLoginSet);
        List<ChatDto> temp = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        list.forEach(e -> {
            ChatDto chatDto = mapper.convertValue(e, ChatDto.class);
            temp.add(chatDto);
        });
        RedisConfig.returnResource(jedis);
        return temp;
    }


}

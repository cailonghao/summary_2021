package com.dubbo.provider.listener;

import com.dubbo.provider.service.ChannelSupervise;
import com.dubbo.provider.service.RedisService;
import com.dubbo.provider.vo.ImMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class AConsumerRedisListener implements MessageListener {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private RedisService redisService;

    /**
     * 业务
     */
    public void doBusiness(Message message) throws JsonProcessingException {
        System.out.println("订阅消息");
        String value = (String) redisTemplate.getValueSerializer().deserialize(message.getBody());
        ObjectMapper mapper = new ObjectMapper();
        ImMessage im = mapper.readValue(value, ImMessage.class);
        if (redisService.getFangjian(im.getSend(), im.getAccept())) {
            String id = redisService.getChannel(im.getAccept());
            if(!StringUtils.isEmpty(id)){
                Channel channel = ChannelSupervise.findChannel(id);
                if (channel != null) {
                    if (channel.isActive()) {
                        List<Object> list = redisService.getFangjianMsg(im.getSend(), im.getAccept());
                        list.forEach(e -> {
                            channel.writeAndFlush(new TextWebSocketFrame(e.toString()));
                        });
                    }
                }
            }
        }
    }

    @SneakyThrows
    @Override
    public void onMessage(Message message, byte[] bytes) {
        doBusiness(message);
    }
}

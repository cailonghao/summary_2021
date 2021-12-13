package com.im.server.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.im.server.dto.MsgDto;
import com.im.server.util.ChannelUtil;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisPubSub;

/**
 * 方法会阻塞
 */
@Slf4j
@Service
public class JedisPubsub extends JedisPubSub {

    @SneakyThrows
    @Override
    public void onMessage(String channel, String message) {
        System.out.println(ChannelUtil.channelIndex.size());
        log.info("订阅消息 ---> {}", message);
        ObjectMapper mapper = new ObjectMapper();
        MsgDto msgDto = mapper.readValue(message, MsgDto.class);
        String channelId = ChannelUtil.channelIndex.get(msgDto.getAccept());
        if (!StringUtils.isEmpty(channelId)) {
            log.info("channelId = {}", channelId);
            Channel nettyChannel = ChannelUtil.getChannelMap(channelId);
            if (nettyChannel != null) {
                DefaultHandle.sendMsg(nettyChannel, msgDto);
            }
        }
    }
}

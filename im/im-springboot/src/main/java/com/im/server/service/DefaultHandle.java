package com.im.server.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.im.server.config.RedisConfig;
import com.im.server.constant.RedisConstant;
import com.im.server.dto.MsgDto;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

@Slf4j
public class DefaultHandle extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        String json = msg.text();
        log.info("json = {}", json);
        ObjectMapper mapper = new ObjectMapper();
        MsgDto msgDto = mapper.readValue(json, MsgDto.class);
        Jedis jedis = RedisConfig.getJedis();
        //验证链接
        if (!(jedis.sismember(RedisConstant.imLoginSet, msgDto.getUserId()))) {
            ctx.writeAndFlush(new TextWebSocketFrame("用户未链接"));
        }
        //发布订阅 userId@accept   accept@userId 订阅此消息
        jedis.publish(RedisConstant.xtchatChannel, json);
        //消息存入缓存
        jedis.lpush(msgDto.getUserId() + "@" + msgDto.getAccept(), json);
        jedis.lpush(msgDto.getAccept() + "@" + msgDto.getUserId(), json);
        //异步同步数据库

        //释放资源
        RedisConfig.returnResource(jedis);
    }
}

package com.im.server.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.im.server.config.RedisConfig;
import com.im.server.constant.RedisConstant;
import com.im.server.dto.MsgDto;
import com.im.server.util.ChannelUtil;
import com.im.server.util.RedisUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class DefaultHandle extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ChannelUtil.setChannelMap(ctx.channel().id().asLongText(), ctx.channel());
        super.channelActive(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        String json = msg.text();
        log.info("json = {}", json);
        ObjectMapper mapper = new ObjectMapper();
        MsgDto msgDto = mapper.readValue(json, MsgDto.class);
        Jedis jedis = RedisConfig.getJedis();
        //验证uuid
        if (!(jedis.sismember(RedisConstant.imLoginSet, msgDto.getUuid()))) {
            msgDto.setOk(false);
            msgDto.setMsg("用户未链接");
            ctx.writeAndFlush(new TextWebSocketFrame(mapper.writeValueAsString(msgDto)));
            ctx.close();
            return;
        } else {
            //用户关联channelId
            if (msgDto.getType() == 1) {
                if (!StringUtils.isEmpty(msgDto.getDirective())) {
                    directive(msgDto, ctx.channel());
                }
            } else {
                if (ChannelUtil.channelIndex.containsKey(msgDto.getUserId())) {
                    System.out.println("2");
                    //发布订阅 userId@accept   accept@userId 订阅此消息
                    jedis.publish(RedisConstant.xtchatChannel, json);
                    //消息存入缓存
                    RedisUtil.cacheChatroom(msgDto.getUserId(), msgDto.getAccept(), json);
                    RedisUtil.cacheChatroom(msgDto.getAccept(), msgDto.getUserId(), json);
                }
            }
        }
        RedisConfig.returnResource(jedis);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        ChannelUtil.removeChannelMap(ctx.channel().id().asLongText());
        super.channelInactive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ChannelUtil.removeChannelMap(ctx.channel().id().asLongText());
        log.info("channel error = {}", cause.getMessage());
        ctx.close();
        super.exceptionCaught(ctx, cause);
    }

    //open-登录 history-选择聊天
    private void directive(MsgDto msgDto, Channel channel) throws JsonProcessingException {
        String dt = msgDto.getDirective();
        if ("open".equals(dt)) {
            ChannelUtil.channelIndex.put(msgDto.getUserId(), channel.id().asLongText());
            msgDto.setOk(true);
            msgDto.setMsg("客户端注册成功");
            sendMsg(channel, msgDto);
        }
        if (dt.startsWith("history")) {
            String curPage = dt.replace("history", "");
            List<String> list = RedisUtil.getChatroom(msgDto.getUserId(), msgDto.getAccept(), Integer.valueOf(curPage));
            sendMsg(channel, list);
        }
    }

    public static void sendMsg(Channel channel, MsgDto msgDto) throws JsonProcessingException {
        List<MsgDto> list = new ArrayList<>();
        list.add(msgDto);
        ObjectMapper mapper = new ObjectMapper();
        channel.writeAndFlush(new TextWebSocketFrame(mapper.writeValueAsString(list)));
    }

    public static void sendMsg(Channel channel, List<String> list) throws JsonProcessingException {
        List<MsgDto> lt = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        list.forEach(e -> {
            try {
                lt.add(mapper.readValue(e, MsgDto.class));
            } catch (JsonProcessingException jsonProcessingException) {
                jsonProcessingException.printStackTrace();
            }
        });
        channel.writeAndFlush(new TextWebSocketFrame(mapper.writeValueAsString(lt)));
    }
}

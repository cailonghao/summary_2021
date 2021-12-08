package com.dubbo.provider.service;

import com.dubbo.provider.ProviderApp;
import com.dubbo.provider.vo.ImMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.UUID;

import static io.netty.handler.codec.http.HttpUtil.isKeepAlive;

/**
 * server配置类
 */
@Slf4j
public class DefaultHandle extends SimpleChannelInboundHandler<Object> {

    private WebSocketServerHandshaker handshaker;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FullHttpRequest) {
            //以http请求形式接入，但是走的是websocket
            handleHttpRequest(ctx, (FullHttpRequest) msg);
        } else if (msg instanceof WebSocketFrame) {
            //处理websocket客户端的消息
            handlerWebSocketFrame(ctx, (WebSocketFrame) msg);
        }
    }

    private void handlerWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
        // 判断是否关闭链路的指令
        if (frame instanceof CloseWebSocketFrame) {
            handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
            return;
        }
        // 判断是否ping消息
        if (frame instanceof PingWebSocketFrame) {
            ctx.channel().write(
                    new PongWebSocketFrame(frame.content().retain()));
            return;
        }
        // 本例程仅支持文本消息，不支持二进制消息
        if (!(frame instanceof TextWebSocketFrame)) {
            log.info("本例程仅支持文本消息，不支持二进制消息");
            throw new UnsupportedOperationException(String.format(
                    "%s frame types not supported", frame.getClass().getName()));
        }
        // 返回应答消息
        String text = ((TextWebSocketFrame) frame).text();
        log.info("服务端收到：" + text);
        ObjectMapper mapper = new ObjectMapper();
        try {
            RedisService redisService = ProviderApp.ctx.getBean(RedisService.class);
            ImMessage im = mapper.readValue(text, ImMessage.class);
            if (redisService.hasUser(im.getUuid())) {
                //检查本地注册表



                //注册进redis 发布登录消息
                redisService.sendTopMessage(text);
                //绑定channel
                redisService.addChannel(im.getSend(), ctx.channel().id().asShortText());
                ChannelSupervise.addChannel(ctx.channel());
                //创建链接列表
                redisService.addSendLineAccept(String.valueOf(im.getSend()),String.valueOf(im.getAccept()));
                redisService.addSendLineAccept(String.valueOf(im.getAccept()),String.valueOf(im.getSend()));
                //存放消息
                redisService.addFangjianMsg(String.valueOf(im.getSend()),String.valueOf(im.getAccept()), text);
            } else {
                ctx.channel().writeAndFlush("验证失败");
                handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
            }
        } catch (Exception e) {
            log.error("解析json失败 e= {}", e.getMessage());
        }
//        TextWebSocketFrame tws = new TextWebSocketFrame(new Date().toString()
//                + ctx.channel().id() + "：" + text);
        // 群发
        //ChannelSupervise.send2All(tws);
        // 返回【谁发的发给谁】
        //ctx.channel().writeAndFlush(tws);
        //System.out.println("毒害亲肤");
//        ctx.channel().writeAndFlush(new TextWebSocketFrame("sadsd"));
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel 关闭");
        ChannelSupervise.removeChannel(ctx.channel());
        super.channelInactive(ctx);
    }

    /**
     * 唯一的一次http请求，用于创建websocket
     */
    private void handleHttpRequest(ChannelHandlerContext ctx,
                                   FullHttpRequest req) {
        log.info("请求连接。。。");
        //要求Upgrade为websocket，过滤掉get/Post
        if (!req.decoderResult().isSuccess()
                || (!"websocket".equals(req.headers().get("Upgrade")))) {
            //若不是websocket方式，则创建BAD_REQUEST的req，返回给客户端
            sendHttpResponse(ctx, req, new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }
        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(
                "ws:/".concat(ctx.channel().localAddress().toString()), null, false);
        handshaker = wsFactory.newHandshaker(req);
        if (handshaker == null) {
            WebSocketServerHandshakerFactory
                    .sendUnsupportedVersionResponse(ctx.channel());
        } else {
            handshaker.handshake(ctx.channel(), req);
        }
    }

    /**
     * 拒绝不合法的请求，并返回错误信息
     */
    private static void sendHttpResponse(ChannelHandlerContext ctx,
                                         FullHttpRequest req, DefaultFullHttpResponse res) {
        // 返回应答给客户端
        if (res.status().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(res.status().toString(),
                    CharsetUtil.UTF_8);
            res.content().writeBytes(buf);
            buf.release();
        }
        ChannelFuture f = ctx.channel().writeAndFlush(res);
        // 如果是非Keep-Alive，关闭连接
        if (!isKeepAlive(req) || res.status().code() != 200) {
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }


    private static Boolean localCache(String uuid){
        if(ChannelSupervise.findChannel(uuid)!=null){
            return true;
        }
        return false;
    }


}

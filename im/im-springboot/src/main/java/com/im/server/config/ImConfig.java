package com.im.server.config;

import com.im.server.constant.RedisConstant;
import com.im.server.service.JedisPubsub;
import com.im.server.service.WebsocketInitialier;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

import java.net.InetSocketAddress;

@Slf4j
public class ImConfig {

    private static final ImConfig imConfig = new ImConfig();

    private ImConfig() {

    }

    public static ImConfig getInstance() {
        return imConfig;
    }


    public void initIm(String port) {
        log.info("im server listen {} ......", port);

        ServerBootstrap bootstrap = new ServerBootstrap();
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();
        EventLoopGroup pubsub = new NioEventLoopGroup();
        pubsub.execute(this::pubsub);
        try {
            bootstrap.
                    group(boss, work)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new WebsocketInitialier());
            ChannelFuture future = bootstrap.bind(new InetSocketAddress(Integer.parseInt(port))).sync();
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }
    }

    public void pubsub() {
        log.info("订阅 {} 频道", RedisConstant.xtchatChannel);
        Jedis jedis = RedisConfig.getJedis();
        jedis.subscribe(new JedisPubsub(), RedisConstant.xtchatChannel);
        RedisConfig.returnResource(jedis);
    }
}

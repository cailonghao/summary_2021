package com.im.client;

import com.im.client.config.RedisConfig;
import com.im.client.config.SocketInitialier;
import com.im.client.config.WebSockerInit;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class Server {
    {
        new RedisConfig().init();
    }

    public static void init() throws InterruptedException {
        System.out.println("socket server listen 44444 ......");
        ServerBootstrap bootstrap = new ServerBootstrap();
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup work = new NioEventLoopGroup();
        try {
            bootstrap.group(boss, work)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new SocketInitialier());
            ChannelFuture channelFuture = bootstrap.bind(44444).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }
    }

    public static void webSocket() throws InterruptedException {
        System.out.println("socket server listen 44444 ......");
        ServerBootstrap bootstrap = new ServerBootstrap();
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup work = new NioEventLoopGroup();
        try {
            bootstrap.group(boss, work)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new WebSockerInit());
            ChannelFuture channelFuture = bootstrap.bind(new InetSocketAddress(44444)).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }
    }
}

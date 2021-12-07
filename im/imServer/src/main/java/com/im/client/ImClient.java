package com.im.client;


import com.im.client.config.RedisConfig;
import com.im.client.config.SocketInitialier;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;


public class ImClient {


    public static void main(String[] args) throws InterruptedException {
        //Server.init();
        Server.webSocket();
    }
}

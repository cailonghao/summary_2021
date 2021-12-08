package com.im.server;

import com.im.server.service.DefaultChannelHandle;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class DefaultInitialier extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast("http", new HttpServerCodec());
        socketChannel.pipeline().addLast("default", new DefaultChannelHandle());
    }



}

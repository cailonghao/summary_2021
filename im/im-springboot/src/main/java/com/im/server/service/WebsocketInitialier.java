package com.im.server.service;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;


public class WebsocketInitialier extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast("http", new HttpServerCodec());
        pipeline.addLast("tk", new ChunkedWriteHandler());
        pipeline.addLast("gr", new HttpObjectAggregator(10 * 1024));
        pipeline.addLast("ws",new WebSocketServerProtocolHandler("/ws"));
        pipeline.addLast("dt", new DefaultHandle());
    }
}

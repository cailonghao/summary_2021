package com.im.client.config;

import com.im.client.handle.DefaultWebSocketHandle;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class WebSockerInit extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast("http",new HttpServerCodec());//http处理器
        pipeline.addLast("chunked",new ChunkedWriteHandler());//用于大数据的分区传输
        pipeline.addLast("agree",new HttpObjectAggregator(8192));//分段处理器
        pipeline.addLast("webSocket",new WebSocketServerProtocolHandler("/ws"));
        pipeline.addLast("default",new DefaultWebSocketHandle());

    }
}

package com.im.client.config;

import com.im.client.handle.DefaultHandle;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.Charset;

/**
 * socket编程
 */
public class SocketInitialier extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        pipeline.addLast("str1", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
        pipeline.addLast("str2", new LengthFieldPrepender(4));
        pipeline.addLast("str3", new StringDecoder(Charset.defaultCharset()));
        pipeline.addLast("str4",new StringEncoder(Charset.defaultCharset()));
        pipeline.addLast("default", new DefaultHandle());
    }
}

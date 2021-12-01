package com.dubbo.provider.config;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Slf4j
@Configuration
public class ImServerConfig {

    @PostConstruct
    public void init() {
        log.info("im server start ......");
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup(4);
        //细分work 处理耗时操作
        EventLoopGroup cache = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        try {
            serverBootstrap.group(boss, work)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(
//                            new ChannelInitializer<SocketChannel>() {
//                                @Override
//                                protected void initChannel(SocketChannel socketChannel) throws Exception {
//                                    //socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
//                                    socketChannel.pipeline().addLast(new DefaultHandle());
//                                }
//                            }
                            new ImWebsocketChannel()
                    );
            ChannelFuture channelFuture = serverBootstrap.bind(50000).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            log.info("im server start fail......");
            log.error(e.getMessage());
        } finally {
            System.out.println("end...");
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }
    }
}

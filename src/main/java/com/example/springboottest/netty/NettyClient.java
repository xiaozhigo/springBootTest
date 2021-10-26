package com.example.springboottest.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * netty客户端
 */
@Slf4j
public class NettyClient {

    public void start(){
        NioEventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap().group(group)
                .option(ChannelOption.TCP_NODELAY, true)//该参数的作用就是禁止使用Nagle算法，使用于小数据即时传输
                .channel(NioSocketChannel.class)
                .handler(new NettyClientInitializer());
        try {
            ChannelFuture future = bootstrap.connect("127.0.0.1", 10080).sync();
            log.info("客户端成功...");
            //发送消息
            future.channel().writeAndFlush("客户端发送消息: {} 二哈");
            //等待连接被关闭
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        NettyClient client = new NettyClient();
        client.start();
    }
}

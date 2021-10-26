package com.example.springboottest.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * 服务启动监听器
 */
@Slf4j
public class NettyServer {

    public static void start(InetSocketAddress socketAddress) {
        //new 一个主线程组
        NioEventLoopGroup group = new NioEventLoopGroup(1);
        //new 一个工作线程组
        NioEventLoopGroup workGroup = new NioEventLoopGroup(200);
        ServerBootstrap bootstrap = new ServerBootstrap().group(group, workGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ServerChannelInitializer())
                .localAddress(socketAddress)
                .option(ChannelOption.SO_BACKLOG, 1024)//设置队列大小
                .childOption(ChannelOption.SO_KEEPALIVE, true);// 两小时内没有数据的通信时,TCP会自动发送一个活动探测数据报文
        //绑定端口,开始接收进来的连接
        try {
            ChannelFuture future = bootstrap.bind(socketAddress).sync();
            log.info("服务器启动开始监听端口: {}", socketAddress.getPort());
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //关闭主线程组
            group.shutdownGracefully();
            //关闭工作线程组
            workGroup.shutdownGracefully();
        }

    }

    public static void main(String[] args) {
        NettyServer server = new NettyServer();
        server.start(new InetSocketAddress("127.0.0.1",10080));
    }
}

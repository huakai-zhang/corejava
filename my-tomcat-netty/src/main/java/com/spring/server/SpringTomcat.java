package com.spring.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

public class SpringTomcat {

    public void start(int port) throws Exception {
        // NIO
        // ServerSocketChannel s = ServerSocketChannel.open();
        // s.bind();

        // BIO
        // ServerSocket s = new ServerSocket(port);

        // 主从模型
        // Boss 线程
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // Worker 线程
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            // Netty 服务
            ServerBootstrap server = new ServerBootstrap();
            // 链路式编程
            server.group(bossGroup, workerGroup)
                    // 主线程处理类
                    .channel(NioServerSocketChannel.class)
                    // 子线程的处理
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            // 无锁化串行编程
                            // 业务逻辑链路，编码器
                            socketChannel.pipeline().addLast(new HttpResponseEncoder());
                            // 解码器
                            socketChannel.pipeline().addLast(new HttpRequestDecoder());

                            // 业务逻辑处理
                            socketChannel.pipeline().addLast(new SpringTomcatHandler());
                        }
                    })
                    // 配置信息，针对主线程的配置
                    .option(ChannelOption.SO_BACKLOG, 128)
                    // 子线程的配置
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture f = server.bind(port).sync();

            System.out.println("SpringTomcat 已启动，端口号：" + port);

            f.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        try {
            new SpringTomcat().start(8080);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

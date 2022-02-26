package com.spring.chat.server;

import com.spring.chat.protocol.IMDecoder;
import com.spring.chat.protocol.IMEncoder;
import com.spring.chat.server.handler.HttpHandler;
import com.spring.chat.server.handler.SocketHandler;
import com.spring.chat.server.handler.WebSocketHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/6/5
 */
public class ChatServer {
    private int port = 80;
    public void start() throws Exception {
        // Boss 线程
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // Worker 线程
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            // 启动引擎
            ServerBootstrap server = new ServerBootstrap();
            // 主从模型
            server.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            // 所有自定义的业务从这开始
                            ChannelPipeline pipeline = socketChannel.pipeline();

                            // ------------------------- 支持自定义Socket协议 -------------------------
                            pipeline.addLast(new IMDecoder());
                            pipeline.addLast(new IMEncoder());
                            pipeline.addLast(new SocketHandler());

                            // ------------------------- 支持HTTP协议 -------------------------
                            // 解码和编码HTTP请求
                            pipeline.addLast(new HttpServerCodec());
                            pipeline.addLast(new HttpObjectAggregator(64 * 1024));
                            // 用于处理文件流的一个Handler
                            pipeline.addLast(new ChunkedWriteHandler());
                            // 用来拦截Http协议
                            pipeline.addLast(new HttpHandler());

                            // ------------------------- 支持WebSocket协议 -------------------------
                            pipeline.addLast(new WebSocketServerProtocolHandler("/im"));
                            pipeline.addLast(new WebSocketHandler());
                        }
                    }).option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            // 等待客户端连接
            ChannelFuture f = server.bind(this.port).sync();

            System.out.println("服务已启动，端口号：" + this.port);

            f.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        try {
            new ChatServer().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.spring.v1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * @author 春阳
 * @date 2021-02-03 16:17
 */
public class HuaKaiRegistry {
    private int port;

    public HuaKaiRegistry(int port) {
        this.port = port;
    }

    public void start() {
        // 主线程初始化
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        // 工作线程初始化
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        HuaKaiHandler huaKaiHandler = new HuaKaiHandler();
        ServerBootstrap server = new ServerBootstrap();
        server.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        // 在netty中，把全部的业务逻辑处理放到一个队列中
                        // 无锁化串行队列
                        ChannelPipeline pipeline = ch.pipeline();

                        // 处理的拆包，粘包的解，编器
                        // maxFrameLength 采用什么样的协议，最大长度是多少，这里采用int，最大为int的最大值
                        // lengthFieldOffset 信息头的偏移量
                        // 每一个属性值的编码长度，int 4 字节
                        pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0 ,4));
                        pipeline.addLast(new LengthFieldPrepender(4));

                        // 处理序列化的解，编码器（JDK默认的序列化）
                        pipeline.addLast("encoder", new ObjectEncoder());
                        pipeline.addLast("decoder", new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)));

                        // 自定义业务逻辑
                        pipeline.addLast(huaKaiHandler);
                    }
                })
                .option(ChannelOption.SO_BACKLOG, 128)
                // 保证每一个子线程可以循环利用
                .childOption(ChannelOption.SO_KEEPALIVE, true);
        try {
            ChannelFuture sync = server.bind(port).sync();
            System.out.println("启动成功，端口：" + port);
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

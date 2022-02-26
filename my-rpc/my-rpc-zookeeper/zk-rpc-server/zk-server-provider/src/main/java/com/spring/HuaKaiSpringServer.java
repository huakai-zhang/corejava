package com.spring;

import com.spring.registry.RegistryCenter;
import com.spring.registry.ZookeeperRegistryCenter;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 春阳
 * @date 2021-02-04 11:52
 */
@Component
public class HuaKaiSpringServer implements ApplicationContextAware, InitializingBean {

    private Map<String, Object> beanMap = new HashMap<>();

    private RegistryCenter registryCenter = new ZookeeperRegistryCenter();

    private int port;

    public HuaKaiSpringServer(int port) {
        this.port = port;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        //接收客户端的链接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //处理已经被接收的链接
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        try {
            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).
                    childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().
                                    addLast(new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null))).
                                    addLast(new ObjectEncoder()).
                                    addLast(new HuaKaiSpringHandler(beanMap));
                        }
                    });
            ChannelFuture sync = serverBootstrap.bind(port).sync();
            System.out.println("服务启动成功，端口：" + port);
            sync.channel().closeFuture().sync();
        } finally {
            System.out.println("服务关闭");
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(RpcService.class);
        if (!beans.isEmpty()) {
            for (Object bean : beans.values()) {
                RpcService rpcService = bean.getClass().getAnnotation(RpcService.class);
                String serverName = rpcService.value().getName();
                String version = rpcService.version();
                if (!StringUtils.isEmpty(version)) {
                    serverName += "-" + version;
                }
                beanMap.put(serverName, bean);
                registryCenter.registry(serverName, getAddress() + ":" + port);
            }
        }
    }

    private static String getAddress(){
        InetAddress inetAddress = null;
        try {
            inetAddress = InetAddress.getLocalHost();
            if (inetAddress != null) {
                // 获得本机的ip地址
                return inetAddress.getHostAddress();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "localhost";
    }
}

package com.spring.consumer;

import com.spring.rpc.core.RpcRequest;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author 春阳
 * @date 2021-02-03 17:21
 */
public class HuaKaiProxy implements InvocationHandler {

    private final CuratorFramework curatorFramework;
    
    private Map<String, List<String>> urls = new HashMap<>();

    public HuaKaiProxy() {
        curatorFramework = CuratorFrameworkFactory.builder().
                connectString("192.168.25.129:2181").sessionTimeoutMs(5000).
                retryPolicy(new ExponentialBackoffRetry(1000,3)).
                namespace("huakai").build();
        curatorFramework.start();
    }

    public <T> T getInstance(Class<?> interfaceClass) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, this);
    }

    /**
     * 代理，调用IRpcHello接口中每一个方法的时候，实际上就是发起一次网络请求
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 如果传进来的是一个已经实现的具体的类，直接忽略
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        } else {
            return rpcInvoke(method, args);
        }
    }

    public Object rpcInvoke(Method method, Object[] args) {
        String className = method.getDeclaringClass().getName();
        RpcRequest request = new RpcRequest();
        request.setClassName(className);
        request.setMethodName(method.getName());
        request.setParameters(args);
        request.setParameterTypes(method.getParameterTypes());

        EventLoopGroup group = new NioEventLoopGroup();
        RpcProxyHandler handler = new RpcProxyHandler();
        try {
            List<String> paths;
            if (urls.containsKey(className)) {
                paths = urls.get(className);
            } else {
                paths = curatorFramework.getChildren().forPath("/" + className);
                urls.put(className, paths);
            }
            String path = URLDecoder.decode(paths.get(new Random().nextInt(paths.size())), "UTF-8");
            String[] split = path.substring(9).split(":");
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();

                            // 处理的拆包，粘包的解，编器
                            pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0 ,4));
                            pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));

                            // 处理序列化的解，编码器（JDK默认的序列化）
                            pipeline.addLast("encoder", new ObjectEncoder());
                            pipeline.addLast("decoder", new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)));

                            // 业务逻辑
                            pipeline.addLast(handler);
                        }
                    });
            ChannelFuture future = b.connect(split[0], Integer.parseInt(split[1])).sync();
            System.out.println("执行端口：" + split[1]);
            future.channel().writeAndFlush(request).sync();
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
        return handler.getResult();
    }

}

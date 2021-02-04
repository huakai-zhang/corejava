package com.spring.v2;

import com.spring.rpc.core.RpcRequest;
import com.spring.v1.HuaKaiProxyHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author 春阳
 * @date 2021-02-03 17:21
 */
public class HuaKaiSpringProxy implements InvocationHandler {

    private String version;

    public <T> T getInstance(Class<?> interfaceClass) {
        return getInstance(interfaceClass, null);
    }

    public <T> T getInstance(Class<?> interfaceClass, String version) {
        this.version = version;
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
        RpcRequest request = new RpcRequest();
        request.setClassName(method.getDeclaringClass().getName());
        request.setMethodName(method.getName());
        request.setParameters(args);
        request.setParameterTypes(method.getParameterTypes());
        request.setVersion(version);
        EventLoopGroup group = new NioEventLoopGroup();
        HuaKaiProxyHandler handler = new HuaKaiProxyHandler();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().
                                    addLast(new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null))).
                                    addLast(new ObjectEncoder())
                                    .addLast(handler);
                        }
                    });
            ChannelFuture future = b.connect("localhost", 8080).sync();
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

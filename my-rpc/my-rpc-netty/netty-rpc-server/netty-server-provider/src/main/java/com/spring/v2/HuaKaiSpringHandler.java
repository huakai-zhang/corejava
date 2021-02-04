package com.spring.v2;

import com.spring.rpc.core.RpcRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 处理整个注册中心的业务逻辑
 * @author 春阳
 * @date 2021-02-03 16:29
 */
@io.netty.channel.ChannelHandler.Sharable
public class HuaKaiSpringHandler extends ChannelInboundHandlerAdapter {

    private Map<String, Object> beanMap = new HashMap<>();

    public HuaKaiSpringHandler(Map<String, Object> beanMap) {
        this.beanMap = beanMap;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Object result = new Object();
        RpcRequest request = (RpcRequest) msg;
        String className = request.getClassName();
        if (request.getVersion() != null) {
            className += "-" + request.getVersion();
        }
        if (beanMap.containsKey(className)) {
            Object clazz = beanMap.get(className);
            Method method = clazz.getClass().getMethod(request.getMethodName(), request.getParameterTypes());
            result = method.invoke(clazz, request.getParameters());
        }
        ctx.writeAndFlush(result);
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}

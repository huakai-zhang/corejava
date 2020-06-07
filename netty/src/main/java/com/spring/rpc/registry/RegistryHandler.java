package com.spring.rpc.registry;

import com.spring.rpc.core.msg.InvokerMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

// 处理整个注册中心的业务逻辑
public class RegistryHandler extends ChannelInboundHandlerAdapter {
    // 在注册中心注册的服务需要一个容器存放
    public static ConcurrentHashMap<String, Object> registryMap = new ConcurrentHashMap<>();

    private List<String> classCache = new ArrayList<>();

    // 约定，只要卸载provider包下面的所有类都认为是一个可以对外提供服务的实现类
    // com.spring.rpc.provider
    public RegistryHandler() {
        scanClass("com.spring.rpc.provider");
        doRegister();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Object result = new Object();
        // 客户端传过来的调用信息
        InvokerMsg request = (InvokerMsg) msg;
        if (registryMap.containsKey(request.getClassName())) {
            Object clazz = registryMap.get(request.getClassName());
            Method method = clazz.getClass().getMethod(request.getMethodName(), request.getParames());
            result = method.invoke(clazz, request.getValues());
        }
        ctx.writeAndFlush(result);
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    // IOC容器大概写一下
    // 扫描出所有的Class
    private void scanClass(String packageName) {
        URL url = this.getClass().getClassLoader().getResource(packageName.replaceAll("\\.", "/"));
        File dir = new File(url.getFile());
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                scanClass(packageName + "." + file.getName());
            } else {
                classCache.add(packageName + "." + file.getName().replace(".class", "").trim());
            }
        }
    }

    // 把扫描到class实例化，放到map中，这就是注册
    // 注册的服务名字，叫接口名字
    private void doRegister() {
        if (classCache.size() == 0) {
            return;
        }

        for (String className : classCache) {
            try {
                Class<?> clazz = Class.forName(className);
                // 服务名称
                Class<?> interfaces = clazz.getInterfaces()[0];

                registryMap.put(interfaces.getName(), clazz.newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

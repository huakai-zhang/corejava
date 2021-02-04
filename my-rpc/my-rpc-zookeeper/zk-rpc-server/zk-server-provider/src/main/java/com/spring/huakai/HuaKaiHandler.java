package com.spring.huakai;

import com.spring.rpc.core.RpcRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 处理整个注册中心的业务逻辑
 * @author 春阳
 * @date 2021-02-03 16:29
 */
@io.netty.channel.ChannelHandler.Sharable
public class HuaKaiHandler extends ChannelInboundHandlerAdapter {

    private int port;

    // 在注册中心注册的服务需要一个容器存放
    public static ConcurrentHashMap<String, Object> registryMap = new ConcurrentHashMap<>();

    private List<String> classCache = new ArrayList<>();

    // 约定，只要卸载provider包下面的所有类都认为是一个可以对外提供服务的实现类
    // com.spring.service
    public HuaKaiHandler(int port) {
        this.port = port;
        scanClass("com.spring.service");
        doRegistry();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Object result = new Object();
        RpcRequest request = (RpcRequest) msg;
        if (registryMap.containsKey(request.getClassName())) {
            Object clazz = registryMap.get(request.getClassName());
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

    // 把扫描到class实例化，放到map中，这就是注册
    // 注册的服务名字，叫接口名字
    private void doRegistry() {
        if (classCache.size() == 0) {
            return;
        }
        try {
            CuratorFramework curatorFramework= CuratorFrameworkFactory.builder().
                    connectString("192.168.25.129:2181").sessionTimeoutMs(5000).
                    retryPolicy(new ExponentialBackoffRetry(1000,3)).
                    namespace("huakai").build();
            curatorFramework.start();
            for (String className : classCache) {
                Class<?> clazz = Class.forName(className);
                Class<?> interfaceClass = clazz.getInterfaces()[0];
                curatorFramework.create().creatingParentsIfNeeded()
                        .withMode(CreateMode.EPHEMERAL).forPath("/" + interfaceClass.getName() + "/" + URLEncoder.encode("huakai://localhost:" + port, "UTF-8"));
                registryMap.put(interfaceClass.getName(), clazz.newInstance());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // IOC容器大概写一下
    private void scanClass(String path) {
        URL url = this.getClass().getClassLoader().getResource(path.replaceAll("\\.", "/"));
        File dir = new File(url.getFile());
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                scanClass(path + "." + file.getName());
            } else {
                classCache.add(path + "." + file.getName().replace(".class", "").trim());
            }
        }
        System.out.println("扫描到的所有服务：" + classCache);
    }
}

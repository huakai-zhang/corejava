package com.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 花开不合阳春暮
 * @date 2021/1/1 下午1:26
 */
@Component
public class SpringRpcServer implements ApplicationContextAware, InitializingBean {

    ExecutorService executorService = Executors.newCachedThreadPool();

    private Map<String, Object> handleMap = new HashMap<>();

    private int port;

    public SpringRpcServer(int port) {
        this.port = port;
    }

    /**
     * InitializingBean 为 bean 提供了初始化方法的方式，它只包括 afterPropertiesSet 方法，凡是继承该接口的类，在初始化 bean 的时候会执行该方法
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                // 不断接受请求
                Socket socket = serverSocket.accept();
                // 每个socket交给线程处理
                executorService.execute(new ProcessorHandler(socket, handleMap));
            }
        } catch (IOException e) {
            if(serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    /**
     * 实现了 ApplicationContextAware 接口的 bean，当 spring 容器初始化的时候，会自动的将 ApplicationContext 注入进来
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Object> serviceBeanMap = applicationContext.getBeansWithAnnotation(RpcService.class);
        if (!serviceBeanMap.isEmpty()) {
            for(Object serviceBean : serviceBeanMap.values()) {
                RpcService rpcService = serviceBean.getClass().getAnnotation(RpcService.class);
                String serviceName = rpcService.value().getName();
                // 拿到版本号
                String version = rpcService.version();
                if(!StringUtils.isEmpty(version)) {
                    serviceName += "-" + version;
                }
                handleMap.put(serviceName, serviceBean);
            }
        }
    }
}

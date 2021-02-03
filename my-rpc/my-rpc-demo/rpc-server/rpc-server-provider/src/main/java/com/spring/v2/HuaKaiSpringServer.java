package com.spring.v2;

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
 * @author 春阳
 * @date 2021-02-03 14:37
 */
@Component
public class HuaKaiSpringServer implements ApplicationContextAware, InitializingBean {

    private Map<String, Object> handleMap = new HashMap<>();

    private int port;

    public HuaKaiSpringServer(int port) {
        this.port = port;
    }

    ExecutorService executorService = Executors.newCachedThreadPool();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // 从上下文中获取带有 RpcService 注解的 bean
        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(RpcService.class);
        if (!beans.isEmpty()) {
            for (Object bean : beans.values()) {
                RpcService rpcService = bean.getClass().getAnnotation(RpcService.class);
                Class<?>[] interfaces = bean.getClass().getInterfaces();
                String serviceName = interfaces[0].getName();
                String version = rpcService.version();
                if (!StringUtils.isEmpty(version)) {
                    serviceName += "-" + version;
                }
                handleMap.put(serviceName, bean);
            }
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                // 不断接受请求
                Socket socket = serverSocket.accept();
                // 每个socket交给线程处理
                executorService.execute(new HuaKaiSpringHandler(socket, handleMap));
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
}

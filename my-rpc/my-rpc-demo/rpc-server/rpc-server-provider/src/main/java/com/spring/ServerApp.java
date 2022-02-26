package com.spring;

import com.spring.v1.HuaKaiServer;
import com.spring.service.HelloServiceImpl;
import com.spring.v2.HuaKaiConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author 花开不合阳春暮
 * @date 2021/1/1 下午12:44
 */
public class ServerApp {
    public static void main(String[] args) {
        /*IHelloService helloService = new HelloServiceImpl();
        new HuaKaiServer(helloService, 8080);*/
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HuaKaiConfig.class);
        context.start();
    }
}

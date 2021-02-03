package com.spring;

import com.spring.v1.HuaKaiProxy;
import com.spring.v2.RpcProxyClient;
import com.spring.v2.SpringConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author 花开不合阳春暮
 * @date 2021/1/1 下午12:47
 */
public class ClientApp {
    public static void main(String[] args) {
        /*IHelloService iHelloService = new HuaKaiProxy("localhost", 8080).getInstance(IHelloService.class);

        String result = iHelloService.syaHello("晓晓");
        System.out.println(result);*/
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        RpcProxyClient rpcProxyClient = context.getBean(RpcProxyClient.class);

        IHelloService iHelloService = rpcProxyClient.clientProxy(IHelloService.class, "localhost", 8080);
        String result = iHelloService.syaHello("Xiaoxiao");
        System.out.println(result);
    }
}

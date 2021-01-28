package com.spring.service.impl;

import com.spring.service.ISayHelloService;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author 花开不合阳春暮
 * @date 2021/1/23 上午11:33
 */
@Service(protocol = {"rest", "dubbo"}, registry = {"provider1", "provider2"})//(timeout = 50000, version = "v1.0.0")//loadbalance = "random", weight = 1
public class ISayHelloServiceImpl implements ISayHelloService {

    @Override
    public String sayHello() {
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("Come in");
        return "Hello Dubbo!";
    }

    @Override
    public String sayBey() {
        return "Dubbo Bye!";
    }
}
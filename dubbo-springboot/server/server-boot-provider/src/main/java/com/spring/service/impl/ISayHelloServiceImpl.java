package com.spring.service.impl;

import com.spring.service.ISayHelloService;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author 花开不合阳春暮
 * @date 2021/1/23 上午11:33
 */
@Service(loadbalance = "random", timeout = 50000)
public class ISayHelloServiceImpl implements ISayHelloService {
    @Override
    public String sayHello() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Come in");
        return "Hello Dubbo!";
    }
}

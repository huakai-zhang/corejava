package com.spring.service;

import com.spring.IHelloService;
import com.spring.v2.RpcService;
import com.spring.User;

/**
 * @author 花开不合阳春暮
 * @date 2021/1/1 下午12:20
 */
@RpcService(value = IHelloService.class, version = "v2.0")
public class HelloServiceImpl1 implements IHelloService {
    @Override
    public String syaHello(String content) {
        System.out.println("【v2.0】request in sayHello: " + content);
        return "【v2.0】Say Hello: " + content;
    }

    @Override
    public String saveUser(User user) {
        return null;
    }
}

package com.spring.service;

import com.spring.rpc.api.IRpcHello;
import com.spring.RpcService;

@RpcService(value = IRpcHello.class, version = "V1.0.0")
public class RpcHello implements IRpcHello {
    @Override
    public String hello(String name) {
        System.out.println("Hello, Zookeeper Netty!!!");
        return "Hello, " + name + "!";
    }
}

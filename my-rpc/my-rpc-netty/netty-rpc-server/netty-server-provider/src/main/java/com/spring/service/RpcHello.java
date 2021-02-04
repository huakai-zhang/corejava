package com.spring.service;

import com.spring.rpc.api.IRpcHello;
import com.spring.v2.RpcService;

@RpcService(value = IRpcHello.class, version = "V1.0.0")
public class RpcHello implements IRpcHello {
    @Override
    public String hello(String name) {
        return "Hello, " + name + "!";
    }
}

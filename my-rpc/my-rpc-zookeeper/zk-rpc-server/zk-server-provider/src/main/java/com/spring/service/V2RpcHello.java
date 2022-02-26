package com.spring.service;

import com.spring.rpc.api.IRpcHello;
import com.spring.RpcService;

@RpcService(value = IRpcHello.class, version = "V2.0.0")
public class V2RpcHello implements IRpcHello {
    @Override
    public String hello(String name) {
        return "V2.0.0, Hello, " + name + "!";
    }
}

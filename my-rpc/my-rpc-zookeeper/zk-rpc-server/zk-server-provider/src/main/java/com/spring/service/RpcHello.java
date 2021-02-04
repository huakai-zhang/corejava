package com.spring.service;

import com.spring.rpc.api.IRpcHello;

public class RpcHello implements IRpcHello {
    @Override
    public String hello(String name) {
        return "Hello, " + name + "!";
    }
}

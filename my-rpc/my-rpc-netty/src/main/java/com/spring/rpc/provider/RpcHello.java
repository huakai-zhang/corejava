package com.spring.rpc.provider;

import com.spring.rpc.api.IRpcHello;

public class RpcHello implements IRpcHello {
    @Override
    public String hello(String name) {
        return "Hello, " + name + "!";
    }
}

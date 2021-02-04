package com.spring.service;

import com.spring.rpc.api.IRpcCalc;
import com.spring.v2.RpcService;

@RpcService(value = IRpcCalc.class)
public class RpcCalc implements IRpcCalc {
    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int sub(int a, int b) {
        return a - b;
    }

    @Override
    public int mult(int a, int b) {
        return a * b;
    }

    @Override
    public int div(int a, int b) {
        return a / b;
    }
}

package com.spring.rpc.provider;

import com.spring.rpc.api.IRpcCalc;
import com.spring.rpc.api.IRpcHello;

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

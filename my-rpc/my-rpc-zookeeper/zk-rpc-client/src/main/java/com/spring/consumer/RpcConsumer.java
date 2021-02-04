package com.spring.consumer;

import com.spring.rpc.api.IRpcCalc;
import com.spring.rpc.api.IRpcHello;

public class RpcConsumer {


    public static void main(String[] args) {
        // 本机调用
        // IRpcHello rpcHello = new RpcHello();
        // rpcHello.hello("晓晓");

        // 动态代理，传递一个接口与，返回一个实例（伪代理）
        HuaKaiProxy huaKaiProxy = new HuaKaiProxy();
        IRpcHello rpcHello = huaKaiProxy.getInstance(IRpcHello.class);
        String r = rpcHello.hello("晓晓");
        System.out.println(r);

        int a = 8, b = 2;
        IRpcCalc calc = huaKaiProxy.getInstance(IRpcCalc.class);
        System.out.println(a + " + " + b + " = " + calc.add(a, b));
        System.out.println(a + " - " + b + " = " + calc.sub(a, b));
        System.out.println(a + " * " + b + " = " + calc.mult(a, b));
        System.out.println(a + " / " + b + " = " + calc.div(a, b));
    }
}

package com.spring;

import com.spring.rpc.api.IRpcCalc;
import com.spring.rpc.api.IRpcHello;
import com.spring.v1.HuaKaiProxy;
import com.spring.v2.HuaKaiSpringProxy;
import com.spring.v2.SpringConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class RpcConsumer {


    public static void main(String[] args) {
        // 本机调用
        // IRpcHello rpcHello = new RpcHello();
        // rpcHello.hello("晓晓");

        // 动态代理，传递一个接口与，返回一个实例（伪代理）
        /*IRpcHello rpcHello = new HuaKaiProxy().getInstance(IRpcHello.class);
        String r = rpcHello.hello("晓晓");
        System.out.println(r);

        int a = 8,b = 2;
        IRpcCalc calc = new HuaKaiProxy().getInstance(IRpcCalc.class);
        System.out.println(a + " + " + b + " = " + calc.add(a, b));
        System.out.println(a + " - " + b + " = " + calc.sub(a, b));
        System.out.println(a + " * " + b + " = " + calc.mult(a, b));
        System.out.println(a + " / " + b + " = " + calc.div(a, b));*/
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        HuaKaiSpringProxy proxy = applicationContext.getBean(HuaKaiSpringProxy.class);

        IRpcHello rpcHello1 = proxy.getInstance(IRpcHello.class, "V1.0.0");
        System.out.println(rpcHello1.hello("晓晓"));

        IRpcHello rpcHello2 = proxy.getInstance(IRpcHello.class, "V2.0.0");
        System.out.println(rpcHello2.hello("XiaoXiao"));

        int a = 8, b = 2;
        IRpcCalc calc = proxy.getInstance(IRpcCalc.class);
        System.out.println(a + " + " + b + " = " + calc.add(a, b));
        System.out.println(a + " - " + b + " = " + calc.sub(a, b));
        System.out.println(a + " * " + b + " = " + calc.mult(a, b));
        System.out.println(a + " / " + b + " = " + calc.div(a, b));
    }
}

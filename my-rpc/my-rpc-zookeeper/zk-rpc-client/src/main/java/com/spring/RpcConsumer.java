package com.spring;

import com.spring.rpc.api.IRpcCalc;
import com.spring.rpc.api.IRpcHello;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class RpcConsumer {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        HuaKaiSpringProxy proxy = applicationContext.getBean(HuaKaiSpringProxy.class);

        IRpcHello rpcHello1 = proxy.getInstance(IRpcHello.class, "V1.0.0");
        for (int i = 0; i < 10; i++) {
            System.out.println(rpcHello1.hello("晓晓"));
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        /*IRpcHello rpcHello2 = proxy.getInstance(IRpcHello.class, "V2.0.0");
        System.out.println(rpcHello2.hello("XiaoXiao"));

        int a = 8, b = 2;
        IRpcCalc calc = proxy.getInstance(IRpcCalc.class);
        System.out.println(a + " + " + b + " = " + calc.add(a, b));
        System.out.println(a + " - " + b + " = " + calc.sub(a, b));
        System.out.println(a + " * " + b + " = " + calc.mult(a, b));
        System.out.println(a + " / " + b + " = " + calc.div(a, b));*/
    }
}

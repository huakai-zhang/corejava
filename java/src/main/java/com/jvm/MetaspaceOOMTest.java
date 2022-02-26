package com.jvm;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/10/9
 */
public class MetaspaceOOMTest {

    static class OOMTest {}

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(10000);
        System.out.println("开始");
        // 模拟计数多少次以后发生异常
        int i = 0;
        try {
            while (true) {
                i++;
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(OOMTest.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        return methodProxy.invokeSuper(o, args);
                    }
                });
                enhancer.create();
                Thread.sleep(50);
            }
        } catch (Throwable e) {
            System.out.println("************多少次后发生异常： " + i);
            e.printStackTrace();
        }
    }
}
// -XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=8m
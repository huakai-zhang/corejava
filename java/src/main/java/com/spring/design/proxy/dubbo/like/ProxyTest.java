package com.spring.design.proxy.dubbo.like;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author 春阳
 * @date 2021-01-29 19:05
 */
public class ProxyTest {
    public static void main(String[] args) throws Throwable {
        final HelloService target = new HelloServiceImpl();
        ProxyFactory factory = factory = new JavassistProxyFactory();

        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) {
                String methodName = method.getName();
                //打印日志
                System.out.println("[before] The method " + methodName + " begins");
                Object result = null;
                try {
                    //前置通知
                    result = method.invoke(target, args);
                    //返回通知, 可以访问到方法的返回值
                    System.out.println(String.format("after method:%s execute", method.getName()));
                } catch (Exception e) {
                    e.printStackTrace();
                    //异常通知, 可以访问到方法出现的异常
                }
                //后置通知. 因为方法可以能会出异常, 所以访问不到方法的返回值
                //打印日志
                System.out.println("[after] The method ends with " + result);
                return result;
            }
        };

        HelloService proxy = factory.getProxy(target);
        System.out.println(proxy);

        proxy.say("ricky");
        proxy.echo("傻吊");
        proxy.getHobbies();
    }
}
    /*public java.lang.String[] getHobbies() {
        Object[] args = new Object[0];
        System.out.println(args);
        return (java.lang.String[]) ret;
    }*/
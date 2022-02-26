package com.spring.design.proxy.javassist;

import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;

/**
 * 通过javassist.util.proxy.ProxyFactory类来生成代理
 * @author 春阳
 * @date 2021-01-29 18:40
 */
public class Client {

    public static void main(String[] args) throws Exception {
        Singer proxy = (Singer) getProxy(Singer.class);
        proxy.sing();
    }

    public static Object getProxy(Class<?> type) throws Exception {
        ProxyFactory f = new ProxyFactory();
        f.setSuperclass(type);
        f.setFilter(m -> {
            // ignore finalize()
            return !m.getName().equals("finalize");
        });

        Class<?> c = f.createClass();
        MethodHandler mi = (self, m, proceed, args) -> {
            System.out.println("method name: " + m.getName()+" exec");
            return proceed.invoke(self, args);  // execute the original method.
        };
        Object proxy = c.newInstance();
        ((ProxyObject)proxy).setHandler(mi);
        return proxy;
    }
}

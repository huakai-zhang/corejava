package com.spring;

/**
 * @author 春阳
 * @date 2021-01-25 19:36
 */
public class SpiServerImplA implements SpiServer {
    @Override
    public void sayHello(String name) {
        System.out.println("Hello, " + name + "! from Service-A");
    }
}

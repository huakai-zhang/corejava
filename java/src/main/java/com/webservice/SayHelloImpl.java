package com.webservice;

import javax.jws.WebService;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/4/9
 */
@WebService
public class SayHelloImpl implements ISayHello {
    @Override
    public String sayHello(String name) {
        System.out.println("call sayHello()");
        return "Hello, " + name;
    }
}

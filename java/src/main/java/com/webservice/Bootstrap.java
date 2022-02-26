package com.webservice;

import javax.xml.ws.Endpoint;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/4/9
 */
public class Bootstrap {
    public static void main(String[] args) {
        // 底层实现 Class.forName("com.sun.net.httpserver.HttpServer");
        Endpoint.publish("http://localhost:8888/hello", new SayHelloImpl());
        System.out.println("publish success!");
    }
}

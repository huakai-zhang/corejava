package com.spring.design.proxy.proxy.jdk;

import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author 春阳
 * @date 2021-03-22 15:13
 */
public class ProxyFileTest {
    public static void main(String[] args) {
        byte[] $Proxy = ProxyGenerator.generateProxyClass("$Proxy", new Class[]{Person.class});
        try {
            FileOutputStream fos = new FileOutputStream("TestProxy.class");
            fos.write($Proxy);
            fos.flush();
            fos.close();
        } catch (Exception e) {

        }
    }
}

package com.spring.rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;

public class HelloClient {
    public static void main(String[] args) {
        try {
            ISayHello hello = (ISayHello) Naming.lookup("rmi://localhost:8888/sayHello");
            System.out.println(hello.sayHello("Hello, Xiaoxiao"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

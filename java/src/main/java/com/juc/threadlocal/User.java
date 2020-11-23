package com.juc.threadlocal;

public class User {
    private byte[] bytes = new byte[5 * 1024 * 1024];
    String name;
    public User(String name){
        this.name = name;
    }
}

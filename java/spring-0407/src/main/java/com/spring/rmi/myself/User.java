package com.spring.rmi.myself;

import java.io.IOException;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/4/9
 */
public class User {

    private int age;

    public int getAge() throws IOException {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

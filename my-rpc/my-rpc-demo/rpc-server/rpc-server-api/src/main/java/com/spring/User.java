package com.spring;

/**
 * @author 花开不合阳春暮
 * @date 2021/1/1 下午12:16
 */
public class User {
    private String name;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.spring.michael;

import java.io.Serializable;

public class Person extends SuperPerson implements Serializable {

    private static final long serialVersionUID = 3670473041324264753L;

    public static int height = 2;

    private String name;

    private transient int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

package com.spring.michael;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import java.io.Serializable;

public class Person extends SuperPerson implements Serializable {

    private static final long serialVersionUID = 3670473041324264753L;

    public static int height = 2;

    @Protobuf(fieldType = FieldType.STRING, order = 1)
    private String name;

    @Protobuf(fieldType = FieldType.INT32, order = 2)
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

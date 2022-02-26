package com.spring.model;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;

import java.io.Serializable;

public class User implements Serializable, BeanNameAware {
    private static final long serialVersionUID = 361091462702868517L;
    private int id;
    private String name;

    public User() {
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public User(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("setBeanName");
    }

    public void display(){
        System.out.println("我是原来的方法");
    }
}

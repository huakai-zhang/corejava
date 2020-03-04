package com.spring.factory.simple;

import com.spring.factory.Audi;
import com.spring.factory.Benz;
import com.spring.factory.Bmw;
import com.spring.factory.Car;

/**
 * 对于这个工厂来说，
 * @author Spring Zhang
 * @date 2020/3/3 16:40
 */
public class SimpleFactory {

    /**
     * 实现统一管理，专业化管理
     * 如果没有工厂模式，小作坊，没有执行标准
     * 这种工厂模式不太合理
     * @param name
     * @return
     */
    public Car getCar(String name) {
        if ("BMW".equalsIgnoreCase(name)) {
            return new Bmw();
        } else if ("Benz".equalsIgnoreCase(name)) {
            return new Benz();
        } else if ("Audi".equalsIgnoreCase(name)) {
            return new Audi();
        } else {
            System.out.println("这个产品没有");
            return null;
        }
    }
}

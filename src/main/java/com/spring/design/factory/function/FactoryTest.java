package com.spring.design.factory.function;

/**
 * @author Spring Zhang
 * @date 2020/3/3 17:27
 */
public class FactoryTest {
    public static void main(String[] args) {
        // 工厂方法模式
        // 各个产品的生产商，都有各自的工厂
        Factory factory = new AudiFactory();
        System.out.println(factory.getCar().getName());
    }
}

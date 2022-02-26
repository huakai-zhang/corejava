package com.spring.design.factory.abst;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/4/2
 */
public class AbstractFactoryTest {
    public static void main(String[] args) {
        AbstractFactory v = new VolkswagenFactory();
        v.makeLavida();
        v.makeSagitar();
        v.makePolo();
    }
}

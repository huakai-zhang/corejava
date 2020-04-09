package com.spring.design.factory.simple;

import com.spring.design.factory.Volkswagen;

/**
 * @author Spring Zhang
 * @date 2020/3/3 17:35
 */
public class SimpleFactoryTest {
    public static void main(String[] args) {
        SimpleFactory factory = new SimpleFactory();
        // 这边就是消费者
        Volkswagen sagitar = factory.makeCar("S");
        Volkswagen lavida = factory.makeCar("L");
        Volkswagen polo = factory.makeCar("P");
    }
}

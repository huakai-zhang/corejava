package com.spring.design.factory;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/4/2
 */
public class Sagitar implements Volkswagen {
    public Sagitar() {
        this.makeCar();
    }

    @Override
    public void makeCar() {
        System.out.println("大众-速腾");
    }
}

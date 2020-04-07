package com.spring.design.factory;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/4/2
 */
public class Polo implements Volkswagen {

    public Polo(){
        this.makeCar();
    }

    @Override
    public void makeCar() {
        System.out.println("polo");
    }
}

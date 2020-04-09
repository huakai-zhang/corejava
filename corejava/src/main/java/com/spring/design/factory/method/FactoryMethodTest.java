package com.spring.design.factory.method;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/4/2
 */
public class FactoryMethodTest {
    public static void main(String[] args) {
        VolkswagenFactory sagitarFactory = new SagitarFactory();
        VolkswagenFactory lavidaFactory = new LavidaFactory();
        VolkswagenFactory poloFactory = new PoloFactory();
        sagitarFactory.makeCar();
        lavidaFactory.makeCar();
        poloFactory.makeCar();
    }
}

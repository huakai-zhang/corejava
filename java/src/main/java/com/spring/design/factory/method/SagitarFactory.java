package com.spring.design.factory.method;

import com.spring.design.factory.Volkswagen;
import com.spring.design.factory.Sagitar;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/4/2
 */
public class SagitarFactory implements VolkswagenFactory {
    @Override
    public Volkswagen makeCar() {
        return new Sagitar();
    }
}

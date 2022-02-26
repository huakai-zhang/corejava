package com.spring.design.factory.method;

import com.spring.design.factory.Lavida;
import com.spring.design.factory.Volkswagen;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/4/2
 */
public class LavidaFactory implements VolkswagenFactory {
    @Override
    public Volkswagen makeCar() {
        return new Lavida();
    }
}

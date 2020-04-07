package com.spring.design.factory.abst;

import com.spring.design.factory.Lavida;
import com.spring.design.factory.Polo;
import com.spring.design.factory.Volkswagen;
import com.spring.design.factory.Sagitar;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/4/2
 */
public class VolkswagenFactory extends AbstractFactory {
    @Override
    public Volkswagen makeSagitar() {
        return new Sagitar();
    }

    @Override
    public Volkswagen makeLavida() {
        return new Lavida();
    }

    @Override
    public Volkswagen makePolo() {
        return new Polo();
    }
}

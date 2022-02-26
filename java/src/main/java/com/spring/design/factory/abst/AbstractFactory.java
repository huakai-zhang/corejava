package com.spring.design.factory.abst;

import com.spring.design.factory.Volkswagen;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/4/2
 */
public abstract class AbstractFactory {
    public abstract Volkswagen makeSagitar();
    public abstract Volkswagen makeLavida();
    public abstract Volkswagen makePolo();
}

package com.spring.design.factory.abstr;

import com.spring.design.factory.Benz;
import com.spring.design.factory.Car;

/**
 * @author Spring Zhang
 * @date 2020/3/3 17:24
 */
public class BenzFactory extends AbstractFactory {
    @Override
    public Car getCar() {
        return new Benz();
    }
}

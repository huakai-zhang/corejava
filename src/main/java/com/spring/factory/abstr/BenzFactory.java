package com.spring.factory.abstr;

import com.spring.factory.Benz;
import com.spring.factory.Car;

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

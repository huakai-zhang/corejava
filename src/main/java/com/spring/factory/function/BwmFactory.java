package com.spring.factory.function;

import com.spring.factory.Bmw;
import com.spring.factory.Car;

/**
 * @author Spring Zhang
 * @date 2020/3/3 17:24
 */
public class BwmFactory implements Factory {
    @Override
    public Car getCar() {
        return new Bmw();
    }
}

package com.spring.factory.function;

import com.spring.factory.Audi;
import com.spring.factory.Car;

/**
 * @author Spring Zhang
 * @date 2020/3/3 17:24
 */
public class AudiFactory implements Factory {
    @Override
    public Car getCar() {
        return new Audi();
    }
}

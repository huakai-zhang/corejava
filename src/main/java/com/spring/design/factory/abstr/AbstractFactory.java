package com.spring.design.factory.abstr;

import com.spring.design.factory.Car;

/**
 * @author Spring Zhang
 * @date 2020/3/3 17:41
 */
public abstract class AbstractFactory {
    protected abstract Car getCar();

    // 这些代码就是动态配置的功能
    // 固定模式的委派
    // 隐式的指定执行工厂
    public Car getCar(String name) {
        if ("BMW".equalsIgnoreCase(name)) {
            // Spring中的工厂模式
            // Bean
            // BeanFactory，生产Bean
            // 单例的Bean，被代理过的Bean，最原始的Bean，List类型的Bean，作用域不同的Bean
            // getBean
            return  new BwmFactory().getCar();
        } else if ("Benz".equalsIgnoreCase(name)) {
            return new BenzFactory().getCar();
        } else if ("Audi".equalsIgnoreCase(name)) {
            return new AudiFactory().getCar();
        } else {
            System.out.println("这个产品没有");
            return null;
        }
    }
}

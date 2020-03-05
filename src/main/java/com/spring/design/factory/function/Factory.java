package com.spring.design.factory.function;

import com.spring.design.factory.Car;

/**
 * 工厂接口，就定义了所有工厂的执行标准
 * 这种工厂模式增加了代码的使用复杂度
 * @author Spring Zhang
 * @date 2020/3/3 17:22
 */
public interface Factory {

    Car getCar();

}

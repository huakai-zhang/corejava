package com.spring.design.factory.simple;

import com.spring.design.factory.Polo;
import com.spring.design.factory.Volkswagen;
import com.spring.design.factory.Lavida;
import com.spring.design.factory.Sagitar;

/**
 * 对于这个工厂来说，
 * @author Spring Zhang
 * @date 2020/3/3 16:40
 */
public class SimpleFactory {

    /**
     * 实现统一管理，专业化管理
     * 如果没有工厂模式，小作坊，没有执行标准
     * 这种工厂模式不太合理
     * @param carType
     * @return
     */
    public Volkswagen makeCar(String carType) {
        if ("S".equalsIgnoreCase(carType)) {
            return new Sagitar();
        } else if ("L".equalsIgnoreCase(carType)) {
            return new Lavida();
        } else if ("P".equalsIgnoreCase(carType)) {
            return new Polo();
        } else {
            System.out.println("无该产品");
            return null;
        }
    }
}

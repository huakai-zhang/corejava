package com.spring.design.adapter;

/**
 * @author 春阳
 * @date 2021-03-08 19:22
 * 插头提供三项供电 Target 目标抽象类 定义客户所需接口，可以是一个抽象类、接口或具体类
 */
public interface ThreePower {
    /**
     * 三项供电
     */
    void powerByThree();
}

package com.spring.design.template;

/**
 * @author Spring Zhang
 * @date 2020/3/5 17:47
 */
public class Tea extends Bevegrage {
    @Override
    public void addCoundiments() {
        System.out.println("添加蜂蜜");
    }

    @Override
    public void pourInCup() {
        System.out.println("将茶叶倒入杯子");
    }
}

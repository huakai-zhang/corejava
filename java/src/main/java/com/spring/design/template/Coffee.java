package com.spring.design.template;

/**
 * @author Spring Zhang
 * @date 2020/3/5 17:45
 */
public class Coffee extends Bevegrage {
    @Override
    public void addCoundiments() {
        System.out.println("添加牛奶");
    }

    @Override
    public void pourInCup() {
        System.out.println("将咖啡倒入杯子");
    }
}

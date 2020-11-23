package com.spring.design.template;

/**
 * @author Spring Zhang
 * @date 2020/3/5 17:39
 */
public abstract class Bevegrage {

    // 不能被重写
    public final void create() {
        //1.把水烧开
        boilWater();
        //2.把杯子准备好，把原材料放到杯子中
        pourInCup();
        //3.用水冲泡
        brew();
        //4.添加辅料
        addCoundiments();
    }

    public abstract void addCoundiments();

    public abstract void pourInCup();

    public void brew() {
        System.out.println("将开水倒入杯中");
    }

    public void boilWater() {
        System.out.println("烧开水，烧到100度可以起锅了");
    }
}

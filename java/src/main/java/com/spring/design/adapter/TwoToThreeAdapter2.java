package com.spring.design.adapter;

/**
 * @author 春阳
 * @date 2021-03-08 19:24
 * 二项转三项的适配器  继承的方式  类
 */
public class TwoToThreeAdapter2 extends TwoPower implements ThreePower {
    @Override
    public void powerByThree() {
        System.out.println("借助继承适配器转化二项电");
        this.powerByTwo();
    }
}

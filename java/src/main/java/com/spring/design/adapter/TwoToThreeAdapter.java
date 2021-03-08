package com.spring.design.adapter;

/**
 * @author 春阳
 * @date 2021-03-08 19:22
 * 二项转三项的适配器  组合的方式  对象适配器
 */
public class TwoToThreeAdapter implements ThreePower {
    /**
     * 使用委托来完成适配
     */
    private TwoPower twoPower;

    public TwoToThreeAdapter(TwoPower twoPower) {
        this.twoPower = twoPower;
    }


    @Override
    public void powerByThree() {
        System.out.println("借助组合适配器转化二项电");
        twoPower.powerByTwo();
    }
}

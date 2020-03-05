package com.spring.design.delegate;

/**
 * @author Spring Zhang
 * @date 2020/3/5 10:26
 */
public class TargetA implements IDelegate {
    @Override
    public void doing() {
        System.out.println("员工A开始执行任务");
    }
}

package com.spring.design.delegate;

/**
 * @author Spring Zhang
 * @date 2020/3/5 10:28
 */
public class TargetB implements IDelegate {
    @Override
    public void doing() {
        System.out.println("员工B开始执行任务");
    }
}

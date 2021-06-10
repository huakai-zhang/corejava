package com.spring.design.delegate.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @author 春阳
 * @date 2021-06-09 15:49
 */
public abstract class HuaKaiApplicationContext {
    public void refresh() {
        obtainFreshBeanFactory();
    }

    protected void obtainFreshBeanFactory() {
        //这里使用了委派设计模式
        refreshBeanFactory();
    }

    protected abstract void refreshBeanFactory();
}

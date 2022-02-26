package com.spring.design.delegate.spring;

import org.springframework.beans.BeansException;

/**
 * @author 春阳
 * @date 2021-06-09 15:48
 */
public abstract class HuaKaiAbstractRefresh extends HuaKaiApplicationContext {
    public HuaKaiAbstractRefresh() {
        super();
    }

    @Override
    protected final void refreshBeanFactory(){
        System.out.println("HuaKaiAbstractRefresh");
    }
}

package com.spring.design.delegate.spring;

import org.springframework.beans.BeansException;

/**
 * @author 春阳
 * @date 2021-06-09 15:53
 */
public class HuaKaiGeneric extends HuaKaiApplicationContext {
    @Override
    protected void refreshBeanFactory() {
        System.out.println("HuaKaiGeneric");
    }
}

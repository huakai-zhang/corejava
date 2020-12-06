package com.spring.framework.beans.config;

/**
 * Created by Tom on 2019/4/13.
 */
public class BeanPostProcessor {

    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return bean;
    }
}

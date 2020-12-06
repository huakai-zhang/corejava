package com.spring.framework.beans;

/**
 * @author 花开不合阳春暮
 * @date 2020/11/23 10:30 下午
 */
public interface BeanFactory {

    /**
     * 根据 beanName 从 IOC 容器中获得一个实例 Bean
     * @param beanName
     * @return
     */
    Object getBean(String beanName);

    Object getBean(Class<?> beanClass);
}

package com.spring.framework.beans.support;

import com.spring.framework.beans.config.BeanDefinition;
import com.spring.framework.context.support.AbstractApplicationContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 花开不合阳春暮
 * @date 2020/11/23 10:30 下午
 */
public class DefaultListableBeanFactory extends AbstractApplicationContext {

    /**
     * 存储注册信息的BeanDefinition
     */
    protected final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);

}

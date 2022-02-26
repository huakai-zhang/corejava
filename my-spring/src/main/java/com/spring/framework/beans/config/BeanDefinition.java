package com.spring.framework.beans.config;

import lombok.Data;

/**
 * @author 花开不合阳春暮
 * @date 2020/11/23 10:30 下午
 */
@Data
public class BeanDefinition {

    private String beanClassName;
    private boolean lazyInit = false;
    private String factoryBeanName;

}

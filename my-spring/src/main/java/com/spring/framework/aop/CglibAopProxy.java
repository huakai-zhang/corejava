package com.spring.framework.aop;

import com.spring.framework.aop.support.AdvisedSupport;

/**
 * @author 花开不合阳春暮
 * @date 2020/11/29 上午11:03
 */
public class CglibAopProxy implements AopProxy {
    public CglibAopProxy(AdvisedSupport config) {
    }

    @Override
    public Object getProxy() {
        return null;
    }

    @Override
    public Object getProxy(ClassLoader classLoader) {
        return null;
    }
}

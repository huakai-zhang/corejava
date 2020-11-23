package com.spring.framework.context;

import com.spring.framework.beans.BeanFactory;
import com.spring.framework.beans.support.DefaultListableBeanFactory;

/**
 * 单例工厂的顶层设计
 * @author 花开不合阳春暮
 * @date 2020/11/23 10:30 下午
 */
public class ApplicationContext extends DefaultListableBeanFactory implements BeanFactory {


    @Override
    protected void refresh() {
        // 1. 定位配置文件

        // 2. 加载配置文件，扫描相关的类，把他们封装成 BeanDefinition

        // 3. 注册，把配置信息放到容器里面（伪IOC容器）

        // 4. 把不是延时加载的类，要提前初始化
    }

    @Override
    public Object getBean(String beanName) {
        return null;
    }

}

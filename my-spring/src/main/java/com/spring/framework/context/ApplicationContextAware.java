package com.spring.framework.context;

/**
 * 通过结偶方式获得IOC容器的顶层设计
 * 后面将通过一个监听器去扫描所有的类，只要实现了此接口
 * 将自动调用setApplicationContext()方法，从而将IOC容器注入到目标类中
 * @author 花开不合阳春暮
 * @date 2020/11/23 10:30 下午
 */
public interface ApplicationContextAware {

    void setApplicationContext(ApplicationContext applicationContext);

}
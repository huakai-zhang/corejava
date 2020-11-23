package com.spring.cyclicdependence;

import org.springframework.beans.factory.ObjectFactory;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/9/23
 */
public class CyclicDependenceTest {

    private static final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);

    private static final Set<String> singletonsCurrentlyInCreation = Collections.newSetFromMap(new ConcurrentHashMap<>(16));

    public static void main(String[] args) {
        Object sharedInstance = doGetBean("A");
    }

    static Object doGetBean(final String namey) {
        Object sharedInstance = getSingleton(namey);
        if (sharedInstance == null) {
            System.out.println("getSingleton---" + namey + "---缓存在和创建中均未找到实例");
            System.out.println("getSingleton---" + namey + "---开始准备创建实例");
            sharedInstance = getSingleton(namey, () -> createBean(namey));
        }
        System.out.println("doGetBean---" + namey + "---成功返回");
        return sharedInstance;
    }

    static Object getSingleton(String beanName) {
        System.out.println("getSingleton---" + beanName + "---从缓存中获取成品的目标对象");
        Object singletonObject = singletonObjects.get(beanName);
        System.out.println("getSingleton---" + beanName + "---如果缓存中不存在目标对象，则判断当前对象是否已经处于创建过程中");
        if (singletonObject == null && isSingletonCurrentlyInCreation(beanName)) {
            System.out.println("getSingleton---" + beanName + "---实例处于创建中直接返回");
            return new A();
        }
        System.out.println("getSingleton---" + beanName + "---未处于创建中直接返回");
        return singletonObject;
    }

    static Object getSingleton(String beanName, ObjectFactory<?> singletonFactory) {
        beforeSingletonCreation(beanName);
        return singletonFactory.getObject();
    }

    static Object createBean(String beanName) {
        System.out.println("createBean---" + beanName + "---创建实例");
        Object beanInstance = doCreateBean(beanName);
        System.out.println("createBean---" + beanName + "---成功返回");
        return beanInstance;
    }

    private static Object doCreateBean(String beanName) {
        System.out.println("doCreateBean---" + beanName + "---实例化bean");
        System.out.println("doCreateBean---" + beanName + "---初始化bean实例");
        populateBean(beanName);
        System.out.println("doCreateBean---" + beanName + "---成功返回");
        return new A();
    }

    private static void populateBean(String beanName) {
        System.out.println("populateBean---" + beanName + "---填充Bean");
        if (beanName.equals("A")) {
            System.out.println("populateBean---" + beanName + "---对象A引用了对象B，需要获取B实例");
            System.out.println("------------------------------------------------分割线------------------------------------------------");
            doGetBean("B");
            System.out.println("populateBean---" + beanName + "---实例A成功引用半成品状态实例B");
            System.out.println("populateBean---" + beanName + "---填充完毕");
        }
        if (beanName.equals("B")) {
            System.out.println("populateBean---" + beanName + "---对象B引用了对象A，需要获取A实例");
            System.out.println("------------------------------------------------分割线------------------------------------------------");
            doGetBean("A");
            System.out.println("populateBean---" + beanName + "---实例B成功引用半成品状态实例A");
            System.out.println("populateBean---" + beanName + "---填充完毕");
            System.out.println("------------------------------------------------分割线------------------------------------------------");
        }
    }

    private static boolean isSingletonCurrentlyInCreation(String beanName) {
        return singletonsCurrentlyInCreation.contains(beanName);
    }

    private static void beforeSingletonCreation(String beanName) {
        System.out.println("beforeSingletonCreation---" + beanName + "---标记对象为半成品状态(singletonsCurrentlyInCreation)");
        singletonsCurrentlyInCreation.add(beanName);
    }
}

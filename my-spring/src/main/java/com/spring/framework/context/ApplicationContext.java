package com.spring.framework.context;

import com.spring.framework.annotation.Autowired;
import com.spring.framework.annotation.Controller;
import com.spring.framework.annotation.Service;
import com.spring.framework.aop.AopProxy;
import com.spring.framework.aop.support.AdvisedSupport;
import com.spring.framework.beans.BeanFactory;
import com.spring.framework.beans.BeanWrapper;
import com.spring.framework.beans.config.BeanDefinition;
import com.spring.framework.beans.config.BeanPostProcessor;
import com.spring.framework.beans.support.BeanDefinitionReader;
import com.spring.framework.beans.support.DefaultListableBeanFactory;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 单例工厂的顶层设计
 * @author 花开不合阳春暮
 * @date 2020/11/23 10:30 下午
 */
public class ApplicationContext extends DefaultListableBeanFactory implements BeanFactory {

    private String[] configLocations;
    private BeanDefinitionReader reader;

    //单例的IOC容器缓存
    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);
    //通用的IOC容器
    private final Map<String, BeanWrapper> factoryBeanInstanceCache = new ConcurrentHashMap<>(16);

    public ApplicationContext(String... configLocations) {
        this.configLocations = configLocations;
        try {
            refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void refresh() throws Exception {
        // 1. 定位配置文件
        reader = new BeanDefinitionReader(this.configLocations);

        // 2. 加载配置文件，扫描相关的类，把他们封装成 BeanDefinition
        List<BeanDefinition> beanDefinitions = reader.loadBeanDefinitions();

        // 3. 注册，把配置信息放到容器里面（伪IOC容器）
        doRegisterBeanDefinition(beanDefinitions);

        // 4. 把不是延时加载的类，要提前初始化
        doAutowired();
    }

    // 只处理非延时加载的情况
    private void doAutowired() {
        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : super.beanDefinitionMap.entrySet()) {
            String beanName = beanDefinitionEntry.getKey();
            if (!beanDefinitionEntry.getValue().isLazyInit()) {
                getBean(beanName);
            }
        }
    }

    private void doRegisterBeanDefinition(List<BeanDefinition> beanDefinitions) throws Exception {
        for (BeanDefinition beanDefinition : beanDefinitions) {
            if(super.beanDefinitionMap.containsKey(beanDefinition.getFactoryBeanName())){
                throw new Exception("The “" + beanDefinition.getFactoryBeanName() + "” is exists!!");
            }
            super.beanDefinitionMap.put(beanDefinition.getFactoryBeanName(), beanDefinition);
        }
    }

    @Override
    public Object getBean(Class<?> beanClass) {
        return getBean(beanClass.getName());
    }

    @Override
    public Object getBean(String beanName) {

        Object instance = null;

        // 工厂模式 + 策略模式
        BeanPostProcessor postProcessor = new BeanPostProcessor();
        postProcessor.postProcessBeforeInitialization(instance, beanName);

        // 1、初始化
        instance = instantiateBean(beanName, this.beanDefinitionMap.get(beanName));
        // 分两步，解决循环依赖

        // (3).把这个对象封装到BeanWrapper中
        BeanWrapper beanWrapper = new BeanWrapper(instance);

        // (4).拿到BeanWrapper之后，保存到IOC容器中
        this.factoryBeanInstanceCache.put(beanName, beanWrapper);

        postProcessor.postProcessAfterInitialization(instance, beanName);

        // 2、注入
        populateBean(beanName, new BeanDefinition(), beanWrapper);

        return this.factoryBeanInstanceCache.get(beanName).getWrappedInstance();
    }

    private void populateBean(String beanName, BeanDefinition beanDefinition, BeanWrapper beanWrapper) {
        Object instance = beanWrapper.getWrappedInstance();

        Class<?> clazz = beanWrapper.getWrappedClass();
        // 判断只有加了注解的类，才执行依赖注入
        if (!(clazz.isAnnotationPresent(Controller.class) || clazz.isAnnotationPresent(Service.class))) {
            return;
        }

        // 获得所有的fields
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (!field.isAnnotationPresent(Autowired.class)) {
                continue;
            }

            Autowired autowired = field.getAnnotation(Autowired.class);

            String autowiredBeanName = autowired.value().trim();
            if ("".equals(autowiredBeanName)) {
                autowiredBeanName = field.getType().getName();
            }

            // 强制访问
            field.setAccessible(true);
            try {
                field.set(instance, this.factoryBeanInstanceCache.get(autowiredBeanName).getWrappedInstance());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }

    private Object instantiateBean(String beanName, BeanDefinition beanDefinition) {
        // (1).拿到要实例化的对象类名
        String className = beanDefinition.getBeanClassName();
        // (2).反射实例化，得到一个对象
        Object instance = null;
        try {
            // 默认单例
            if (this.singletonObjects.containsKey(className)) {
                instance = this.singletonObjects.get(className);
            } else {
                Class<?> clazz = Class.forName(className);
                instance = clazz.newInstance();

                AdvisedSupport config = instantionAopConfig(beanDefinition);
                config.setTargetClass(clazz);
                config.setTarget(instance);

                if (className.pointCutMatch()) {

                }


                this.singletonObjects.put(className, instance);
                this.singletonObjects.put(beanDefinition.getFactoryBeanName(), instance);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return instance;
    }

    private AdvisedSupport instantionAopConfig(BeanDefinition beanDefinition) {
    }

    public String[] getBeanDefinitionNames() {
        return this.beanDefinitionMap.keySet().toArray(new String[this.beanDefinitionMap.size()]);
    }

    public int getBeanDefinitionCount() {
        return this.beanDefinitionMap.size();
    }

    public Properties getConfig() {
        return this.reader.getConfig();
    }
}

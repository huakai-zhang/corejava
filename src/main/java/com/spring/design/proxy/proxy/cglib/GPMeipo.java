package com.spring.design.proxy.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author Spring Zhang
 * @date 2020/3/3 14:21
 */
public class GPMeipo implements MethodInterceptor {

    public Object getInstance(Class clazz) throws Exception {
        Enhancer enhancer = new Enhancer();
        // 把父类设置为谁
        // 这一步就是告诉CGLib生成的子类需要继承那个类
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        // 1.生成源代码
        // 2.编译class文件
        // 3.加载到jvm中，并返回被代理对象
        return enhancer.create();
    }

    /**
     * 同样是做了字节码重组
     * 对于使用API的用户来说，是无感知的
     */
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("我是GP媒婆，得给你找个异性才行");
        System.out.println("开始进行海选....");
        System.out.println("---------------");
        // 这个o的应用是有CGLib给我们new出来的
        // CGLib new出来以后的对象，是被代理对象的子类（继承了自己写的那个类）
        // OOP，在new子类之前，实际上默认先调用了我们的super()方法的，
        // new了子类的同时，必须先new出来父类，这就相当于间接的持有了我们父类的引用
        // 子类重写了父类的所有方法
        // 我们改变了子类对象的某些属性，是可以间接的操作到父类的属性的
        methodProxy.invokeSuper(o, objects);
        System.out.println("---------------");
        System.out.println("如果合适的话，就准备办事");
        return null;
    }

}

package com.spring.design.proxy.proxy.cglib;

/**
 * @author Spring Zhang
 * @date 2020/3/3 14:48
 */
public class TestCglib {
    public static void main(String[] args) {
        // 代理模式，字节码重组
        // 可以在每一个方法调用之前加一些代码，在方法调用之后再加一些代码
        // AOP:事务代理（声明式事务，那个方法需要加事务，那个方法不需要事务）、日志监听
        // service方法
        // 开启一个事务（open）
        /**
         * 事务执行(由我们代码完成)
         */
        // 监听到是否异常，可能需要根据异常的类型来决定这个事务是否需要回滚还是继续提交
        // 事务关闭（close）

        // JDK的动态代理是通过接口来进行强制转换的
        // 生成以后的代理对象，可以强制转换为接口

        // CGLib的动态代理是通过生成一个被代理对象的子类，然后重写父类的方法
        // 生成以后的对象，可以强制转换为被代理对象（也就是用自己写的类）
        // 子类引用赋值给父类

        try {
            LiSi obj = (LiSi)new GPMeipo().getInstance(LiSi.class);
            obj.findLove();
            obj.test(); // 无法执行代理内容，只能正常调用
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

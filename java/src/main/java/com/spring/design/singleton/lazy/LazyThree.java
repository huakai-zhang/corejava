package com.spring.design.singleton.lazy;

/**
 * 懒汉式，默认加载的时候不实例化，在需要用到这个实例时候才实例化
 * 1. 静态内部类是在被调用时才会被加载，这种方案实现了懒汉单例的一种思想，需要用到的时候才去创建单例，加上
 *    JVM的特性，这种方式又实现了线程安全的创建单例对象。
 * 2.通过对比基于volatile的双重检查锁定方案和基于类初始化方案的对比，我们会发现基于类初始化的方案的实现代
 *   码更简洁。但是基于volatile的双重检查锁定方案有一个额外的优势：除了可以对静态字段实现延迟加载初始化外，
 *   还可以对实例字段实现延迟初始化。
 * @author Spring Zhang
 * @date 2020/3/4 15:37
 */
public class LazyThree {

    //private static boolean initialized = false;

    // 默认使用LazyThree的时候，会先初始化内部类
    // 如果没有使用的话，内部类是不加载的
    // 如果默认构造方法不私有化，相当于有一个默认的public的无参构造方法，就意味着在代码中随时可以new出来
    private LazyThree(){
        // 防止反射侵入
        /*synchronized (LazyThree.class) {
            if (!initialized) {
                initialized = !initialized;
            } else {
                throw new RuntimeException("单例已被侵犯");
            }
        }*/
    }

    public static LazyThree getInstance() {
        return LazyHolder.LAZY;
    }

    private static class LazyHolder {
        private static final LazyThree LAZY = new LazyThree();
    }
}

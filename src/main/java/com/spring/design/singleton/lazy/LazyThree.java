package com.spring.design.singleton.lazy;

/**
 * 懒汉式，默认加载的时候不实例化，在需要用到这个实例时候才实例化
 * @author Spring Zhang
 * @date 2020/3/4 15:37
 */
public class LazyThree {

    private static boolean initialized = false;

    // 默认使用LazyThree的时候，会先初始化内部类
    // 如果没有使用的话，内部类是不加载的
    // 如果默认构造方法不私有化，相当于有一个默认的public的无参构造方法，就意味着在代码中随时可以new出来
    private LazyThree(){
        // 防止反射侵入
        synchronized (LazyThree.class) {
            if (!initialized) {
                initialized = !initialized;
            } else {
                throw new RuntimeException("单例已被侵犯");
            }
        }
    }

    public static LazyThree getInstance() {
        return LazyHolder.LAZY;
    }

    private static class LazyHolder {
        private static final LazyThree LAZY = new LazyThree();
    }
}

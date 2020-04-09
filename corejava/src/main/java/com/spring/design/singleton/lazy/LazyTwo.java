package com.spring.design.singleton.lazy;

/**
 * 懒汉式
 * @author Spring Zhang
 * @date 2020/3/4 15:21
 */
public class LazyTwo {

    private LazyTwo() {}

    private static LazyTwo lazy = null;

    // 不使用synchronized，会出现线程不安全
    // 使用synchronized，性能较差
    public synchronized static LazyTwo getInstance() {
        if (lazy == null) {
            lazy = new LazyTwo();
        }
        return lazy;
    }
}

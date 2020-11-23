package com.spring.design.singleton.lazy;

/**
 * 懒汉式
 * @author Spring Zhang
 * @date 2020/3/4 15:21
 */
public class LazyOne {

    private LazyOne() {}

    private static LazyOne lazy = null;

    public static LazyOne getInstance() {
        if (lazy == null) {
            lazy = new LazyOne();
        }
        return lazy;
    }
}

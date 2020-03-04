package com.spring.singleton.hungry;

/**
 * 饿汉式
 * 在实例使用之前，不管用不用，都先new一个，避免线程安全问题
 * @author Spring Zhang
 * @date 2020/3/4 15:06
 */
public class Hungry {

    private Hungry(){}

    //类加载顺序，先静态，后动态，先属性，后方法，先上，后下
    private static final Hungry hungry = new Hungry();

    public static Hungry getInstance() {
        System.out.println(System.currentTimeMillis() + ":" + hungry);
        return hungry;
    }

}
package com.spring.design.singleton.register;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 注册登记式，每使用一次，都往一个固定的容器中注册并且将使用过的对象进行缓存
 * 下次取对象时候，就直接从缓存中取值，以保证每次获取的都是统一对象
 * IOC中的单例模式，就是典型的注册登记式单例
 * @author Spring Zhang
 * @date 2020/3/4 16:13
 */
public class RegisterMap {
    private RegisterMap(){}

    // ConcurrentHashMap线程安全，HashMap
    private static Map<String, Object> register = new ConcurrentHashMap<>();

    public static RegisterMap getInstance(String name) {
        if (name == null) {
            name = RegisterMap.class.getName();
        }
        if (register.get(name) == null) {
            try {
                register.put(name, new RegisterMap());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return (RegisterMap) register.get(name);
    }
}

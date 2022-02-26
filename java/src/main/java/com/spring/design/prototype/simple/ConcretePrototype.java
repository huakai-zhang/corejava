package com.spring.design.prototype.simple;

import java.util.ArrayList;

/**
 * 原型模式
 * 过程相同，但结果不一样
 * 数据内容完全一样，但实例不同
 * @author Spring Zhang
 * @date 2020/3/5 13:39
 */
public class ConcretePrototype implements Cloneable  {
    public int age;

    public ArrayList<String> list = new ArrayList<>();

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    protected Object clone() {
        ConcretePrototype prototype = null;
        try {
            prototype = (ConcretePrototype) super.clone();
            // 深克隆
            prototype.list = (ArrayList) list.clone();

            // 克隆基于字节码的
            // 用反射或者循环
        } catch (Exception e) {

        }
        return prototype;
    }
}

package com.spring.design.prototype.simple;

/**
 * @author Spring Zhang
 * @date 2020/3/5 13:42
 */
public class CloneTest {
    public static void main(String[] args) {
        try {
            ConcretePrototype cp = new ConcretePrototype();
            cp.setAge(18);
            ConcretePrototype copy = (ConcretePrototype) cp.clone();
            copy.setAge(20);

            //结果为true，证明两个对象cp和copy虽然地址不同，但是公用同一个list地址
            System.out.println(cp.list == copy.list);
            System.out.println(cp.getAge());
            System.out.println(copy.getAge());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // 就是一个现成的对象，这个对象里面已经有设置好的值
    // 当我要新建一个对象，并且要给新建的对象赋值，赋值内容要跟之前的一模一样

    // 传统做法
    // ConcretePrototype cp = new ConcretePrototype();
    // cp.setAge(18);
    // ConcretePrototype copy = new ConcretePrototype();
    // copy.setAge(cp.getAge());

    // 浅拷贝：能够直接拷贝其实际内容的数据类型，只支持9中，八大基本数据类型和String
    // 克隆是不走构造方法，直接是字节流复制
}

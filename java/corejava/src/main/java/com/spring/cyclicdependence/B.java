package com.spring.cyclicdependence;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/9/23
 */
public class B {

    private A a;

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }
}

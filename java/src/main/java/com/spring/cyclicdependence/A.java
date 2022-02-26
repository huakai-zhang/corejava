package com.spring.cyclicdependence;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/9/23
 */
public class A {
    @Autowired
    private B b;

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }
}

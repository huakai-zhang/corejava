package com.spring.dubbo.order;

import java.io.Serializable;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/4/14
 */
public class DoOrderRequest implements Serializable {
    private static final long serialVersionUID = 4296088277401004361L;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DoOrderRequest{" +
                "name='" + name + '\'' +
                '}';
    }
}

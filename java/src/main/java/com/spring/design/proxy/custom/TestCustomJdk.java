package com.spring.design.proxy.custom;

import com.spring.design.proxy.proxy.jdk.Person;
import com.spring.design.proxy.proxy.jdk.XiaoXingxing;

/**
 * @author 春阳
 * @date 2021-03-22 14:54
 */
public class TestCustomJdk {
    public static void main(String[] args) throws Exception {
        Person obj = (Person) new GPMeipo().getInstance(new XiaoXingxing());
        obj.findLove();
    }
}

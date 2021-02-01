package com.spring.design.proxy.javassist.dubbo.like;

/**
 * @author 春阳
 * @date 2021-01-29 19:04
 */
public interface HelloService {
    void say(String msg);

    String echo(String msg);

    String[] getHobbies(Integer id, String name);
}

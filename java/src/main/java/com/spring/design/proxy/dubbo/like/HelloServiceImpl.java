package com.spring.design.proxy.dubbo.like;

/**
 * @author 春阳
 * @date 2021-01-29 19:06
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public void say(String msg) {

    }

    @Override
    public String echo(String msg) {
        return null;
    }

    @Override
    public String[] getHobbies() {
        return new String[0];
    }
}

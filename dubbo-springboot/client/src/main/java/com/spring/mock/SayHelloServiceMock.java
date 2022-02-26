package com.spring.mock;

import com.spring.service.ISayHelloService;

/**
 * @author 花开不合阳春暮
 * @date 2021/1/23 下午12:50
 */
public class SayHelloServiceMock implements ISayHelloService {
    @Override
    public String sayHello() {
        return "服务发生异常，被降级";
    }

    @Override
    public String sayBey() {
        return null;
    }
}

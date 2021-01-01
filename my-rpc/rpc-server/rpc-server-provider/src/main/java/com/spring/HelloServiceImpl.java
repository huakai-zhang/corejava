package com.spring;

/**
 * @author 花开不合阳春暮
 * @date 2021/1/1 下午12:20
 */
@RpcService(value = IHelloService.class, version = "v1.0")
public class HelloServiceImpl implements IHelloService {
    @Override
    public String syaHello(String content) {
        System.out.println("【v1.0】request in sayHello: " + content);
        return "【v1.0】Say Hello: " + content;
    }

    @Override
    public String saveUser(User user) {
        return null;
    }
}

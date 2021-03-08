package com.spring.design.adapter.spring;

/**
 * @author 花开不合阳春暮
 * @date 2021/3/8 下午11:03
 */
public class HuaKaiRequestMappingHandlerAdapter implements HuaKaiHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return handler instanceof HandlerMethod;
    }

    @Override
    public void handle() {
        System.out.println("访问动态资源");
    }
}

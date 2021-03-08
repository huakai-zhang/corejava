package com.spring.design.adapter.spring;

/**
 * @author 花开不合阳春暮
 * @date 2021/3/8 下午10:39
 */
public class RequestMappingHandlerMapping implements HandlerMapping {
    @Override
    public Object getHandler(String request)   {
        if (!request.contains("static")) {
            return new HandlerMethod();
        }
        return null;
    }
}

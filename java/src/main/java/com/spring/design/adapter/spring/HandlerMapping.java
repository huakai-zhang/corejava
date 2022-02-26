package com.spring.design.adapter.spring;

import org.springframework.web.servlet.HandlerExecutionChain;

/**
 * @author 花开不合阳春暮
 * @date 2021/3/8 下午10:38
 */
public interface HandlerMapping {
    Object getHandler(String request) ;
}

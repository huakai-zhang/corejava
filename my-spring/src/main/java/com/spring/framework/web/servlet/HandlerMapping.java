package com.spring.framework.web.servlet;

import lombok.Data;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * @author 花开不合阳春暮
 * @date 2020/11/28 下午4:44
 */
@Data
public class HandlerMapping {

    /**
     * 保存方法对应的实例
     */
    private Object controller;
    /**
     * 保存映射的方法
     */
    private Method method;
    /**
     * url的正则匹配
     */
    private Pattern pattern;

    public HandlerMapping(Pattern pattern, Object controller, Method method) {
        this.controller = controller;
        this.method = method;
        this.pattern = pattern;
    }
}

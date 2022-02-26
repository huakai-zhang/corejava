package com.spring.framework.web.servlet;

import lombok.Data;

import java.util.Map;

/**
 * @author 花开不合阳春暮
 * @date 2020/11/28 下午5:02
 */
@Data
public class ModelAndView {

    private String viewName;

    private Map<String, ?> model;


    public ModelAndView(String viewName) {
        this.viewName = viewName;
    }

    public ModelAndView(String viewName, Map<String, ?> model) {
        this.viewName = viewName;
        this.model = model;
    }
}

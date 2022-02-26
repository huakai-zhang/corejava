package com.spring.design.adapter.spring;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 花开不合阳春暮
 * @date 2021/3/8 下午10:48
 */
public class DispatcherServlet {

    public static List<HandlerMapping> handlerMappings = new ArrayList<>();

    public static List<HuaKaiHandlerAdapter> handlerAdapters = new ArrayList<>();

    static {
        handlerMappings.add(new RequestMappingHandlerMapping());
        handlerMappings.add(new SimpleUrlHandlerMapping());
        handlerAdapters.add(new HuaKaiHttpRequestHandlerAdapter());
        handlerAdapters.add(new HuaKaiRequestMappingHandlerAdapter());
    }

    public static void main(String[] args) {
        DispatcherServlet dispatcherServlet = new DispatcherServlet();
        Object handler = dispatcherServlet.getHandler("static/index.js");

        HuaKaiHandlerAdapter ha = getHandlerAdapter(handler);

        ha.handle();
    }

    private static HuaKaiHandlerAdapter getHandlerAdapter(Object handler) {
        if (handlerAdapters != null) {
            for (HuaKaiHandlerAdapter adapter : handlerAdapters) {
                if (adapter.supports(handler)) {
                    return adapter;
                }
            }
        }
        return null;
    }

    protected Object getHandler(String request) {
        if (handlerMappings != null) {
            for (HandlerMapping mapping : handlerMappings) {
                Object handler = mapping.getHandler(request);
                if (handler != null) {
                    return handler;
                }
            }
        }
        return null;
    }
}

package com.spring.intercept;

import com.alibaba.fastjson.JSONObject;
import com.spring.dubbo.user.IUserService;
import com.spring.dubbo.user.dto.UserRequest;
import com.spring.dubbo.user.dto.UserResponse;
import com.spring.suport.Anonymous;
import com.spring.suport.BaseController;
import com.spring.utils.CookieUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/6/12
 */
public class LoginIntercept extends HandlerInterceptorAdapter {

    private final String ACCESS_TOKEN = "access_token";

    @Autowired
    IUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        Object action = handlerMethod.getBean();
        if (!(action instanceof BaseController)) {
            throw new Exception("错误");
        }
        if (isAnonymous(handlerMethod)) {
            return true;
        }
        String accessToken = CookieUtil.getCookieValue(request, ACCESS_TOKEN);
        if (StringUtils.isEmpty(accessToken)) {
            if (CookieUtil.isAjax(request)) {
                JSONObject object = new JSONObject();
                object.put("code", "-1");
                object.put("msg", "没有登录");
                response.getWriter().write(object.toJSONString());
                return false;
            }
            response.sendRedirect("/error");
            return false;
        }

        UserRequest userRequest = new UserRequest();
        userRequest.setToken(accessToken);
        UserResponse userResponse = userService.checkAuth(userRequest);
        if ("000000".equals(userResponse.getCode())) {
            ((BaseController) action).setUid(userResponse.getUid());
            return true;
        }
        return false;
    }

    private boolean isAnonymous(HandlerMethod handlerMethod) {
        Object action = handlerMethod.getBean();
        Class clazz = action.getClass();
        if (clazz.getAnnotation(Anonymous.class) != null) {
            return true;
        }
        Method method = handlerMethod.getMethod();
        return method.getAnnotation(Anonymous.class) != null;
    }
}

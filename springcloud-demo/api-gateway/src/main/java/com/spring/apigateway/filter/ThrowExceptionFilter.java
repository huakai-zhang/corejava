package com.spring.apigateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.ERROR_TYPE;

/**
 * @author 春阳
 * @date 2021-01-13 16:40
 */
@Component
public class ThrowExceptionFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(ThrowExceptionFilter.class);

    @Override
    public String filterType() {
        return ERROR_TYPE;
    }

    @Override
    public int filterOrder() {
        //需要在默认的 SendErrorFilter 之前
        return -1;
    }

    @Override
    public boolean shouldFilter() {
        // 只有在抛出异常时才会进行拦截
        return RequestContext.getCurrentContext().containsKey("throwable");
    }

    @Override
    public Object run() {
        log.info("This is a pre filter, it will throw a RuntimeException");
        RequestContext ctx = RequestContext.getCurrentContext();
        Throwable throwable = ctx.getThrowable();

        // 删除该异常信息,不然在下一个过滤器中还会被执行处理
        ctx.remove("throwable");

        //响应信息
        HttpServletResponse response = ctx.getResponse();
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setHeader("Error", "Internal Server Error");
        response.setCharacterEncoding("UTF-8");

        // 响应给客户端信息
        PrintWriter pw = null;
        JSONObject ret = new JSONObject();
        ret.put("timestamp", System.currentTimeMillis());
        ret.put("status", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        ret.put("exception", throwable.getClass());
        ret.put("message", throwable.getMessage());
        try {
            pw = response.getWriter();
            pw.write(ret.toJSONString());
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


}

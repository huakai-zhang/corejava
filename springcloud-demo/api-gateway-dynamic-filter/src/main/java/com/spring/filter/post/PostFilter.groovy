package com.spring.filter.pre;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse;

/**
 * @author 春阳
 * @date 2021-01-14 18:49
 */
class PostFilter extends ZuulFilter {

    Logger log = LoggerFactory.getLogger(PostFilter.class)

    @Override
    String filterType() {
        return "post"
    }

    @Override
    int filterOrder() {
        return 2000
    }

    @Override
    boolean shouldFilter() {
        return true
    }

    @Override
    Object run() {
        log.info("this is a post filter: Receive response")
        HttpServletResponse response = RequestContext.getCurrentContext().getResponse()
        response.getOutputStream().print(", I love Xiaoxiao")
        response.flushBuffer()
    }
}
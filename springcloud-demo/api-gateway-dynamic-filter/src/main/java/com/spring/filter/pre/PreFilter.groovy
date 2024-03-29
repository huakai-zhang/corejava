package com.spring.filter.pre;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 春阳
 * @date 2021-01-14 18:49
 */
class PreFilter extends ZuulFilter {

    Logger log = LoggerFactory.getLogger(PreFilter.class)

    @Override
    String filterType() {
        return "pre"
    }

    @Override
    int filterOrder() {
        return 1000
    }

    @Override
    boolean shouldFilter() {
        return true
    }

    @Override
    Object run() {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest()
        log.info("this is a pre filter: Send {} request to {}", request.getMethod(), request.getRequestURL().toString())
        return null
    }
}

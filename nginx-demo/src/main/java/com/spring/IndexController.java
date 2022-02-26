package com.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/6/30
 */
@RestController
@Slf4j
public class IndexController {

    @GetMapping("getIp")
    public String getIp(HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();
        String ngip = request.getHeader("X-Real_IP");
        return remoteAddr + "->" + ngip;
    }

}

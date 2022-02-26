package com.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 花开不合阳春暮
 * @date 2021/1/16 下午4:36
 */
@RestController
@EnableDiscoveryClient
@SpringBootApplication
public class Track2Application {

    private Logger log = LoggerFactory.getLogger(Track2Application.class);

    @GetMapping("/track-2")
    public String track(HttpServletRequest request) {
        log.info("=== call track-2, TraceId={}, SpanId={} ===", request.getHeader("X-B3-TraceId"), request.getHeader("X-B3-SpanId"));
        return "Track";
    }

    public static void main(String[] args) {
        SpringApplication.run(Track2Application.class, args);
    }

}
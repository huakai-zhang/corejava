package com.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 花开不合阳春暮
 * @date 2021/1/16 下午4:36
 */
@RestController
@EnableDiscoveryClient
@SpringBootApplication
public class Track1Application {

    private Logger log = LoggerFactory.getLogger(Track1Application.class);

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @GetMapping("/track-1")
    public String track() {
        log.info("=== call track1 ===");
        return restTemplate().getForEntity("http://track-2/track-2", String.class).getBody();
    }

    public static void main(String[] args) {
        SpringApplication.run(Track1Application.class, args);
    }


}

package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author 春阳
 * @date 2021-01-19 13:31
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

    @RequestMapping(value = "/track-1", method = RequestMethod.GET)
    public String track() {
        log.info("=== call track-1 ===");
        return restTemplate().getForEntity("http://track-2/track-2", String.class).getBody();
    }

    public static void main(String[] args) {
        SpringApplication.run(Track1Application.class, args);
    }

}

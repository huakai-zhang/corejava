package com.spring.feignconsumer.service;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author 春阳
 * @date 2021-01-13 13:47
 */
@FeignClient("hello-service")
public interface RefactorHelloService extends com.spring.helloservice.serivce.HelloService {
}

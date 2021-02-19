package com.spring.provider;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 春阳
 * @date 2021-02-19 16:57
 */
@FeignClient(name = "nacos-server")
@Primary
public interface NacosProvider {

    @GetMapping(value = "/nacos")
    String nacos();

}

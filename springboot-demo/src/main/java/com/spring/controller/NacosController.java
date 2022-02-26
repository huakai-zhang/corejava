package com.spring.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 春阳
 * @date 2021-02-26 15:49
 */
@RestController
@NacosPropertySource(dataId = "spring", groupId = "DEFAULT_GROUP", autoRefreshed = true)
public class NacosController {

    @NacosValue(value = "${info:hello Nacos}", autoRefreshed = true)
    private String info;

    @GetMapping("nacos")
    public String nacos() {
        return info;
    }

}

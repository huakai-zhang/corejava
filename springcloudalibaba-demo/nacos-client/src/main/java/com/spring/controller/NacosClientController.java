package com.spring.controller;

import com.spring.provider.NacosProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 春阳
 * @date 2021-02-19 17:08
 */
@RestController
public class NacosClientController {

    @Autowired
    private NacosProvider nacosProvider;

    @GetMapping("/nacos")
    public String nacos() {
        return "Client: " + nacosProvider.nacos();
    }

}

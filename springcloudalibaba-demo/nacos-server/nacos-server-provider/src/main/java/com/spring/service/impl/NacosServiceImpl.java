package com.spring.service.impl;

import com.spring.service.NacosService;
import org.springframework.stereotype.Service;

/**
 * @author 春阳
 * @date 2021-02-19 16:52
 */
@Service
public class NacosServiceImpl implements NacosService {
    @Override
    public String nacos() {
        System.out.println("Hello Nacos!");
        return "Hello Nacos!";
    }
}

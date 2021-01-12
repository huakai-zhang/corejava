package com.spring.helloservice.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

/**
 * @author 春阳
 * @date 2021-01-11 17:33
 */
@RestController
public class HelloController {

    @Qualifier("eurekaRegistration")
    @Autowired
    private Registration registration;

    @Autowired
    private DiscoveryClient client;

    @GetMapping("/hello")
    public String index() throws InterruptedException {
        ServiceInstance instance = serviceInstance();
        int sleepTime = new Random().nextInt(5000);
        System.out.println("sleepTime:" + sleepTime);
        Thread.sleep(sleepTime);
        System.out.println("/hello, host:" + instance.getHost()  + ", service_id:" + instance.getServiceId());
        return "Hello World";
    }

    public ServiceInstance serviceInstance() {
        List<ServiceInstance> list = client.getInstances(registration.getServiceId());
        if (list != null && list.size() > 0) {
            for(ServiceInstance itm : list){
                if(itm.getPort() == 8080) {
                    return itm;
                }
            }
        }
        return null;
    }
}

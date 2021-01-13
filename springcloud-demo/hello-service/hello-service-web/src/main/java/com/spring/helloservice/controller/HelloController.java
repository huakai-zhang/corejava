package com.spring.helloservice.controller;

import com.spring.helloservice.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.*;

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
        int sleepTime = new Random().nextInt(3000);
        System.out.println("sleepTime:" + sleepTime);
        //Thread.sleep(sleepTime);
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

    @GetMapping("/hello1")
    public String hello(@RequestParam String name)   {
        return "Hello " + name;
    }

    @GetMapping("/hello2")
    public User hello(@RequestParam String name, @RequestParam Integer age)   {
        return new User(name, age);
    }

    @PostMapping("/hello3")
    public String hello(@RequestBody User user)   {
        return "Hello " + user.getName() + ", " + user.getAge();
    }
}

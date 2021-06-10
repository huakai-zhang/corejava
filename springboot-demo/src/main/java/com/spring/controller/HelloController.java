package com.spring.controller;

import com.spring.entity.ApiUser;
import com.spring.entity.MyBean;
import com.spring.entity.User;
import com.spring.service.AsyncHandlerTask;
import com.spring.starter.HelloFormatTemplate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Api(value = "HelloController", tags = "测试API", description = "")
@Slf4j
public class HelloController {

    //@Value("${dev.value}")
    //private String value;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private AsyncHandlerTask asyncHandlerTask;

    @Autowired
    HelloFormatTemplate helloFormatTemplate;

    @Autowired
    ApplicationContext applicationContext;

    @GetMapping("/hello")
    public String hello() {
        //rabbitTemplate.convertAndSend("", "work", "work消息");
        log.info(Thread.currentThread().getName() + " start");
        /*try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        //log.info(value);
        return "Hello Swagger!";
    }

    @GetMapping("/get_user")
    @ApiOperation("spring 的接口")
    public ApiUser getUser(@ApiParam("这个名字会被返回") String username) {
        return new ApiUser();
    }

    @GetMapping("/task")
    @ApiOperation("task 的接口")
    public void task() {
        asyncHandlerTask.sendMessage();
    }

    @GetMapping("/rabbit")
    @ApiOperation("rabbit 5分钟 的接口")
    public void rabbit() {
        rabbitTemplate.convertAndSend("DZ_REDIS_EXCHANGE", "dz.redis", "10秒延时消息");
    }

    @GetMapping("/redis")
    public void redis() {
        redisTemplate.opsForValue().set("xiaoxiao", "spring");
    }

    @GetMapping("/format")
    public String format(){
        User user=new User();
        user.setId(1);
        user.setAccount("Xiaoxiao");
        return helloFormatTemplate.doFormat(user);
    }

    @GetMapping("/bean")
    public void bean() {
        System.out.println(applicationContext.getBean("myBean").hashCode());
        System.out.println(applicationContext.getBean("myBean").hashCode());
    }
}

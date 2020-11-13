package com.spring.boot;

import com.spring.service.AsyncHandlerTask;
import com.spring.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDemoApplicationTests {

    @Autowired
    private AsyncHandlerTask asyncHandlerTask;

    @Autowired
    private UserService userService;

    @Test
    public void contextLoads() {
        for (int i = 0; i < 20; i++) {
            asyncHandlerTask.sendMessage();
        }
    }

    @Test
    public void getUser() {
        System.out.println(userService.getUserByAccount("ed2e0c87-2559-11eb-9365-0c9d9211a1c7"));
    }
}

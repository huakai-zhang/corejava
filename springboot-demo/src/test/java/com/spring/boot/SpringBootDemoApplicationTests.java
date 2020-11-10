package com.spring.boot;

import com.spring.service.AsyncHandlerTask;
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

    @Test
    public void contextLoads() {
        for (int i = 0; i < 20; i++) {
            asyncHandlerTask.sendMessage();
        }
    }

}

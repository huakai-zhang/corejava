package com.spring.boot;

import com.spring.boot.service.AsyncHandlerTask;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootDemoApplicationTests {

    @Autowired
    private AsyncHandlerTask asyncHandlerTask;

    @Test
    void contextLoads() {
        for (int i = 0; i < 20; i++) {
            asyncHandlerTask.sendMessage();
        }
    }

}

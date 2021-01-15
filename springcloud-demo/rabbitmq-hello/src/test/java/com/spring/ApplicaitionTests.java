package com.spring;

import com.spring.rabbitmq.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 春阳
 * @date 2021-01-15 11:05
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicaitionTests {
    @Autowired
    private Sender sender;

    @Test
    public void hello() {
        sender.sent();
    }
}

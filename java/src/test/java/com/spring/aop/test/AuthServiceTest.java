package com.spring.aop.test;

import com.spring.aop.service.AuthServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = {"classpath*:application-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class AuthServiceTest {

    @Autowired
    AuthServiceImpl authService;

    @Test
    public void testAdd() {
        authService.login("", "");
    }
}
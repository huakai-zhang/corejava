package com.spring.aop.test;

import com.spring.model.User;
import com.spring.mybatis.dao.UserMapper;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@ContextConfiguration(locations = {"classpath*:application-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserMapperTest {

    @Autowired
    private UserMapper mapper;

    @Test
    @Ignore
    public void testAnnotation() throws Exception {
        List<User> userList = mapper.selectAll();
        System.out.println(userList);
    }

    @Test
    public void selectAll() throws Exception {
        List<User> userList = mapper.selectAll();
        System.out.println(userList);
    }

    @Test
    @Ignore
    public void selectById() throws Exception {
        User user = mapper.selectById(1);
        System.out.println(user);
    }
}
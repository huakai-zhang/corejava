package com.spring.aop.test;

import com.github.pagehelper.PageHelper;
import com.spring.model.User;
import com.spring.mybatis.dao.UserMapper;
import com.spring.mybatis.sessiontemplate.impl.UserTemplateDaoImpl;
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
    private UserMapper userMapper;

    @Autowired
    private UserTemplateDaoImpl userTemplateDao;

    @Test
    @Ignore
    public void testAnnotation() throws Exception {
        List<User> userList = userMapper.selectAll();
        System.out.println(userList);
    }

    @Test
    public void selectAll() throws Exception {
        //PageHelper.startPage(2, 10);
        List<User> userList = userMapper.selectAll();
        System.out.println(userList);
    }

    @Test
    public void selectById() throws Exception {
        //User user = userMapper.selectById(1);
        User user = userTemplateDao.selectById(1);
        System.out.println(user);
    }

    @Test
    @Ignore
    public void insertUser() throws Exception {
        userMapper.insertUser(new User("Xiaoxiao"));
    }
}
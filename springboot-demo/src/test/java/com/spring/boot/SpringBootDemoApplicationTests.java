package com.spring.boot;

import com.spring.entity.User;
import com.spring.mapper.UserMapper;
import com.spring.service.AsyncHandlerTask;
import com.spring.service.UserService;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDemoApplicationTests {

    @Autowired
    private AsyncHandlerTask asyncHandlerTask;

    @Autowired
    private UserService userService;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Test
    public void contextLoads() {
        for (int i = 0; i < 20; i++) {
            asyncHandlerTask.sendMessage();
        }
    }

    @Test
    public void getUser() {
        System.out.println(userService.getUserByCondition(1L, null, "ed2e0c87-2559-11eb-9365-0c9d9211a1c7"));
    }

    @Test
    public void scan1() {
        SqlSession sqlSession = sqlSessionFactory.openSession();  // 1
        Cursor<User> cursor =
                sqlSession.getMapper(UserMapper.class).scan(1);
        cursor.forEach(user -> {
            System.out.println(user);
        });
    }

    @Test
    @Transactional
    public void scan2() {
        Cursor<User> cursor = userService.scan(1);
        cursor.forEach(user -> {
            System.out.println(user);
        });
    }

    @Test
    public void scan3() {
        transactionTemplate.execute(status -> {               // 2
            try (Cursor<User> cursor = userService.scan(1)) {
                cursor.forEach(user -> {
                    System.out.println(user);
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        });
    }

}

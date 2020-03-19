package com.mybatis.spring;

import com.mybatis.spring.config.Configuration;
import com.mybatis.spring.config.mappers.UserMapper;
import com.mybatis.spring.executor.ExecutorFactory;
import com.mybatis.spring.session.SqlSession;
import com.spring.model.User;

import java.io.IOException;

public class BootStrap {
    public static void main(String[] args) throws IOException {
        start();
    }

    private static void start() throws IOException {
        Configuration configuration = new Configuration();
        configuration.setScanPath("com.mybatis.spring.config.mappers");
        configuration.build();
        SqlSession sqlSession = new SqlSession(configuration,
                ExecutorFactory.get(ExecutorFactory.ExecutorType.CACHING.name(), configuration));
        //SqlSession sqlSession = new SqlSession(configuration, ExecutorFactory.DEFAULT(configuration));
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectByPrimaryKey(1);
        user = userMapper.selectByPrimaryKey(1);
        System.out.println(user);
    }
}

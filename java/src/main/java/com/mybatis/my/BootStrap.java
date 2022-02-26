package com.mybatis.my;

import com.mybatis.my.mapper.UserMapper;
import com.mybatis.my.session.SpringSqlSession;
import com.spring.model.User;

public class BootStrap {
    public static void start(){
        SpringSqlSession sqlSession = new SpringSqlSession();
        UserMapper testMapper = sqlSession.getMapper(UserMapper.class);
        User test = testMapper.selectByPrimaryKey(1);
        System.out.println(test);
    }

    public static void main(String[] args) {
        start();
    }
}
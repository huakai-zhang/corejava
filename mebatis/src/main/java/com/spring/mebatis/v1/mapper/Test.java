package com.spring.mebatis.v1.mapper;

import com.spring.mebatis.v1.Configuration;
import com.spring.mebatis.v1.Executor;
import com.spring.mebatis.v1.SqlSession;

public class Test {
    public static void main(String[] args) {
        SqlSession sqlSession = new SqlSession(new Configuration(), new Executor());
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.selectUserById(1);
    }
}

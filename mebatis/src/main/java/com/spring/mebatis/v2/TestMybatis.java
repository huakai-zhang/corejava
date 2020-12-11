package com.spring.mebatis.v2;

import com.spring.mebatis.v2.mapper.User;
import com.spring.mebatis.v2.mapper.UserMapper;
import com.spring.mebatis.v2.session.DefaultSqlSession;
import com.spring.mebatis.v2.session.SqlSessionFactory;

public class TestMybatis {

    public static void main(String[] args) {
        SqlSessionFactory factory = new SqlSessionFactory();
        DefaultSqlSession sqlSession = factory.build().openSqlSession();
        // 获取MapperProxy代理
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User blog = mapper.selectUserById(1);

        System.out.println("第一次查询: " + blog);
        System.out.println();
        blog = mapper.selectUserById(1);
        System.out.println("第二次查询: " + blog);
    }
}

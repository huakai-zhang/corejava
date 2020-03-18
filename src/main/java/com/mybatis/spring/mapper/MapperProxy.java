package com.mybatis.spring.mapper;

import com.mybatis.spring.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MapperProxy<T> implements InvocationHandler {

    private final SqlSession sqlSession;
    private final Class<T> mappperInterface;

    public MapperProxy(SqlSession sqlSession, Class<T> clazz) {
        this.sqlSession = sqlSession;
        this.mappperInterface = clazz;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        sqlSession.getConfiguration().getMapperRegistory().get()
        return null;
    }
}

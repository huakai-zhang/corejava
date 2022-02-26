package com.mybatis.my.mapper;

import com.mybatis.my.session.SpringSqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MapperProxy<T> implements InvocationHandler {
    private final SpringSqlSession sqlSession;
    private final Class<T> mapperInterface;

    public MapperProxy(SpringSqlSession springSqlSession, Class<T> mapperInterface) {
        this.sqlSession = springSqlSession;
        this.mapperInterface = mapperInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getDeclaringClass().getName().equals(UserMapperXml.nameSpace)) {
            String sql = UserMapperXml.methodSqlMapping.get(method.getName());
            System.out.println(String.format("SQL [ %s ], parameter [%s] ", sql, args[0]));
            return sqlSession.selectOne(sql, String.valueOf(args[0]));
        }
        return null;
    }
}

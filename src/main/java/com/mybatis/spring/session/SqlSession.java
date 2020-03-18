package com.mybatis.spring.session;

import com.mybatis.spring.config.Configuration;
import com.mybatis.spring.executor.Executor;
import com.mybatis.spring.mapper.MapperProxy;

import java.lang.reflect.Proxy;

public class SqlSession {

    private Configuration configuration;
    private Executor executor;

    public SqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public <T>T getMapper(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(),
                new Class[]{clazz}, new MapperProxy(this, clazz));
    }

    public <T>T selectOne(String statement, Object parameter) {
        return executor.query(statement, parameter);
    }

}

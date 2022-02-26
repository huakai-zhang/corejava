package com.mybatis.my.session;

import com.mybatis.my.config.SpringConfiguration;
import com.mybatis.my.executor.SimpleExecutor;
import com.mybatis.my.executor.SpringExecutor;
import com.mybatis.my.mapper.MapperProxy;

import java.lang.reflect.Proxy;

public class SpringSqlSession {

    /*private SpringConfiguration configuration;
    private SpringExecutor executor;

    // 关联起来
    public SpringSqlSession(SpringConfiguration configuration, SpringExecutor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }*/

    private SpringExecutor executor = new SimpleExecutor();
    // TODO configuration

    public <T>T getMapper(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(),
                new Class[]{clazz}, new MapperProxy(this, clazz));
    }

    public <T>T selectOne(String statement, Object parameter) {
        return executor.query(statement, parameter);
    }

    /*public SpringConfiguration getConfiguration() {
        return configuration;
    }*/
}

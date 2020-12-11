package com.spring.mebatis.v1;

import java.lang.reflect.Proxy;
import java.util.ResourceBundle;

/**
 * @author 春阳
 * @date 2020-12-11 13:54
 */
public class Configuration {

    public static final ResourceBundle sqlMappings;

    static{
        sqlMappings = ResourceBundle.getBundle("v1sql");
    }

    public <T> T getMapper(Class clazz, SqlSession sqlSession) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(),
                new Class[]{clazz},
                new MapperProxy(sqlSession));
    }
}

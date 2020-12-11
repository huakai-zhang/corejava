package com.spring.design.proxy.proxy.mybatis;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 春阳
 * @date 2020-12-11 10:54
 */
public class MybatisJdkProxyTest {
    private static final Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap<>();

    static {
        knownMappers.put(JdkProxyUserMapper.class, new MapperProxyFactory<>(JdkProxyUserMapper.class));
    }

    public static void main(String[] args) {
        JdkProxyUserMapper mapper = getMapper(JdkProxyUserMapper.class);
        System.out.println(mapper.getClass());
        mapper.select();
        mapper.selectAll();
    }

    public static  <T> T getMapper(Class<T> type) {
        final MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) knownMappers.get(type);
        return mapperProxyFactory.newInstance("哈哈");
    }
}

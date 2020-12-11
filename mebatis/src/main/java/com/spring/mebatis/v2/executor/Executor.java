package com.spring.mebatis.v2.executor;

/**
 * @author 春阳
 * @date 2020-12-11 14:37
 */
public interface Executor {
    <T> T query(String statement, Object[] parameter, Class pojo);
}

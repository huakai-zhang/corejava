package com.mybatis.my.executor;

public interface SpringExecutor {

    <T> T query(String statement, Object parameter);
}

package com.mybatis.spring.executor;

import com.mybatis.spring.config.MapperRegistory;

public interface Executor {
    <T> T query(MapperRegistory.MapperData mapperData, Object parameter) throws Exception;
}

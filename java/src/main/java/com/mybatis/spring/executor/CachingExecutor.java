package com.mybatis.spring.executor;

import com.mybatis.spring.config.Configuration;
import com.mybatis.spring.config.MapperRegistory;
import com.mybatis.spring.statement.StatementHandler;

import java.util.HashMap;
import java.util.Map;

public class CachingExecutor implements Executor {

    private Configuration configuration;

    private SimpleExecutor delegate;

    private Map<String, Object> localCache = new HashMap<>();

    public CachingExecutor(SimpleExecutor delegate) {
        this.delegate = delegate;
    }

    public CachingExecutor(Configuration configuration) {
        this.configuration = configuration;
    }

    public <E> E query(MapperRegistory.MapperData mapperData, Object parameter) throws Exception {
        //初始化StatementHandler --> ParameterHandler --> ResultSetHandler
        StatementHandler handler = new StatementHandler(configuration);
        Object result = localCache.get(mapperData.getSql());
        if (null != result) {
            System.out.println("缓存命中");
            return (E)result;
        }
        result = (E) delegate.query(mapperData, parameter);
        localCache.put(mapperData.getSql(), result);
        return (E)result;
    }
}

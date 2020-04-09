package com.mybatis.spring.executor;

import com.mybatis.spring.config.Configuration;
import com.mybatis.spring.config.MapperRegistory;
import com.mybatis.spring.statement.StatementHandler;

public class SimpleExecutor implements Executor {

    private Configuration configuration;

    public SimpleExecutor(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <E> E query(MapperRegistory.MapperData mapperData, Object parameter) throws Exception {
        //初始化StatementHandler --> ParameterHandler --> ResultSetHandler
        StatementHandler handler = new StatementHandler(configuration);
        return (E) handler.query(mapperData, parameter);
    }
}

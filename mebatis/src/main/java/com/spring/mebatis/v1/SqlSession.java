package com.spring.mebatis.v1;

/**
 * @author 春阳
 * @date 2020-12-11 13:53
 */
public class SqlSession {

    private Configuration configuration;
    private Executor executor;

    public SqlSession(Configuration configuration, Executor executor){
        this.configuration = configuration;
        this.executor = executor;
    }

    /**
     * 查询方法
     * @param statement 根据 statementId 找到 SQL 语句
     * @param parameter 的 parameter 参数（可以是 Integer 也可以是 String 等等，任意类型），用来填充 SQL 里面的占位符
     * @param <T>
     * @return
     */
    public <T> T selectOne(String statement, Object parameter) {
        String sql = Configuration.sqlMappings.getString(statement);
        if( null != sql && !"".equals(sql)){
            return executor.query(sql, parameter);
        }
        return null;
    }

    /**
     * 获取代理对象
     */
    public <T> T getMapper(Class clazz){
        return (T) configuration.getMapper(clazz, this);
    }

}

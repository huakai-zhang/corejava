package com.spring.mybatis.sessiontemplate;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 春阳
 * @date 2020-12-09 15:48
 */
public class BaseDao extends SqlSessionDaoSupport {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    public Object selectOne(String statement, Object parameter) {
        return getSqlSession().selectOne(statement, parameter);
    }

}

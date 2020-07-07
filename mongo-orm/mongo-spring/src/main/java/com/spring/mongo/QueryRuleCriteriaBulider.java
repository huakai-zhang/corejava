package com.spring.mongo;

import org.springframework.data.mongodb.core.query.Criteria;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/7/7
 */
public class QueryRuleCriteriaBulider {

    public QueryRuleCriteriaBulider (QueryRule queryRule) {

    }

    public Criteria getCriteria() {
        Criteria criteria = new Criteria();
        return criteria;
    }

}

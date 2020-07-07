package com.spring.mongo;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/7/7
 */
public class QueryRuleBuilder {

    public QueryRuleBuilder(QueryRule queryRule) {
        //
        queryRule.getRuleList();
    }

    public Query getQuery() {
        Query query = new Query(new Criteria());
        return query;
    }

}

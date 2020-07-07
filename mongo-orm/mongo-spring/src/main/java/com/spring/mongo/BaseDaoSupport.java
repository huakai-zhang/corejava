package com.spring.mongo;

import com.spring.utils.GenericsUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.io.Serializable;
import java.util.List;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/7/7
 */
public abstract class BaseDaoSupport<T extends Serializable, PK extends Serializable> {

    private MongoTemplate mongoTemplate;

    private EntityOperation op;

    public BaseDaoSupport() {
        Class<T> clazz = GenericsUtils.getSuperClassGenricType(getClass(), 0);
        op = new EntityOperation<>(clazz);
    }

    protected List<T> find(QueryRule queryRule) {

        QueryRuleCriteriaBulider bulider = new QueryRuleCriteriaBulider(queryRule);


        return mongoTemplate.find(null, op.entityClass);
    }

    protected abstract String getPKColumn();

    protected void setTemplate(MongoTemplate template) {
        this.mongoTemplate = template;
    }

}

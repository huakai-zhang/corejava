package com.spring.mongo;

import com.spring.utils.GenericsUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

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

        QueryRuleBuilder builder = new QueryRuleBuilder(queryRule);

        return mongoTemplate.find(builder.getQuery(), op.entityClass);
    }

    protected T get(PK id) throws Exception {
        QueryRule queryRule = QueryRule.getInstance();
        queryRule.andEqual(this.getPKColumn(), id);
        QueryRuleBuilder builder = new QueryRuleBuilder(queryRule);
        Query query = builder.getQuery();
        return (T) mongoTemplate.findOne(query, op.entityClass);
    }

    protected Page<T> find (QueryRule queryRule, final int pageNo, final int pageSize) {
        return null;
    }

    protected Page<T> findPage(Map<String, ?> param, final int pageNo, final int pageSize ) {
        return null;
    }

    protected Page<Map<String, Object>> findPage(Object[] param, final int pageNo, final int pageSize ) {
        return null;
    }

    protected T findUnique(Map<String, Object> properties) {
        return null;
    }

    protected abstract String getPKColumn();

    protected void setTemplate(MongoTemplate template) {
        this.mongoTemplate = template;
    }

}

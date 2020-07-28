package com.spring.mongo;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/7/7
 */
public class QueryRuleBuilder {

    private Query query;

    private Criteria criteria;

    private List<Sort.Order> orders;

    public QueryRuleBuilder(QueryRule queryRule) {
        this.criteria = new Criteria();
        // 根据RuleList来循环，动态生成各种Criteria
        for (QueryRule.Rule rule : queryRule.getRuleList()) {
            switch (rule.getType()) {
                case QueryRule.BETWEEN:
                    processBetween(rule);
                    break;
                case QueryRule.EQ:
                    processEqual(rule);
                    break;
                case QueryRule.LIKE:
                    processLike(rule);
                    break;
                case QueryRule.NOTEQ:
                    processNotEqual(rule);
                    break;
                case QueryRule.GT:
                    processGreaterThen(rule);
                    break;
                case QueryRule.GE:
                    processGreaterEqual(rule);
                    break;
                case QueryRule.LT:
                    processLessThen(rule);
                    break;
                case QueryRule.LE:
                    processLessEqual(rule);
                    break;
                case QueryRule.IN:
                    processIN(rule);
                    break;
                case QueryRule.NOTIN:
                    processNotIN(rule);
                    break;
                case QueryRule.ISNULL:
                    processIsNull(rule);
                    break;
                case QueryRule.ISNOTNULL:
                    processIsNotNull(rule);
                    break;
                case QueryRule.ISEMPTY:
                    processIsEmpty(rule);
                    break;
                case QueryRule.ISNOTEMPTY:
                    processIsNotEmpty(rule);
                    break;
                case QueryRule.ASC_ORDER:
                    processOrder(rule);
                    break;
                case QueryRule.DESC_ORDER:
                    processOrder(rule);
                    break;
                default:
                    throw new IllegalArgumentException("type " + rule.getType() + " not supported.");
            }
        }

        this.query = new Query(this.criteria);
        // 设置排序的优先级
        /*List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.ASC, "age"));*/
        this.query.with(new Sort(this.orders));
    }

    private void processIN(QueryRule.Rule rule) {
    }

    private void processLessEqual(QueryRule.Rule rule) {
    }

    private void processLessThen(QueryRule.Rule rule) {
    }

    private void processGreaterEqual(QueryRule.Rule rule) {
    }

    private void processGreaterThen(QueryRule.Rule rule) {
    }

    private void processNotEqual(QueryRule.Rule rule) {
    }

    private void processLike(QueryRule.Rule rule) {
    }

    private void processNotIN(QueryRule.Rule rule) {
    }

    private void processIsNull(QueryRule.Rule rule) {
    }

    private void processIsNotNull(QueryRule.Rule rule) {
    }

    private void processIsEmpty(QueryRule.Rule rule) {
    }

    private void processIsNotEmpty(QueryRule.Rule rule) {
    }

    private void processOrder(QueryRule.Rule rule) {
        if (null == this.orders) {
            this.orders = new ArrayList<>();
        }
        // 根据某个字段升序、降序
        switch (rule.getType()) {
            case QueryRule.ASC_ORDER:
                // propertyName非空
                if (!StringUtils.isEmpty(rule.getPropertyName())) {
                    this.orders.add(new Sort.Order(Sort.Direction.ASC, rule.getPropertyName()));
                }
                break;
            case QueryRule.DESC_ORDER:
                // propertyName非空
                if (!StringUtils.isEmpty(rule.getPropertyName())) {
                    this.orders.add(new Sort.Order(Sort.Direction.DESC, rule.getPropertyName()));
                }
                break;
            default:
                break;
        }
    }

    private void processEqual(QueryRule.Rule rule) {
        this.criteria.and(rule.getPropertyName()).is(rule.getValues()[0]);
    }

    private void processBetween(QueryRule.Rule rule) {
    }

    public Query getQuery() {

        return query;
    }

}

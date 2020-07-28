package com.spring.demo.dao;

import com.spring.demo.entity.Member;
import com.spring.mongo.BaseDaoSupport;
import com.spring.mongo.QueryRule;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/7/7
 */
@Repository
public class MemberDao extends BaseDaoSupport<Member, Long> {

    public List<Member> getById(String id) {
        QueryRule queryRule = QueryRule.getInstance();
        queryRule.andEqual(this.getPKColumn(), id);
        return super.find(queryRule);
    }

    public List<Member> getAll() {
        QueryRule queryRule = QueryRule.getInstance();
        queryRule.addDescOrder("age");
        return super.find(queryRule);
    }

    @Override
    protected String getPKColumn() {
        return "_id";
    }

    @Override
    @Resource(name = "mongoTemplate")
    protected void setTemplate(MongoTemplate template) {
        super.setTemplate(template);
    }
}

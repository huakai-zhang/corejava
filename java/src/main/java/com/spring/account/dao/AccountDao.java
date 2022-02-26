package com.spring.account.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Repository
public class AccountDao {

    private JdbcTemplate template;

    @Resource(name="dataSource")
    public void setDataSource(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }

    public Double selectAccount(String name) {
        String sql = "select money from account where name = ?";
        Double money = template.queryForObject(sql, new Object[]{name}, Double.class);
        return money;
    }

    /**
     * 转出
     * @param out 由哪个帐号转出
     * @param money 转出多少钱
     * @throws Exception
     */
    public int updateForOut(String out, Double money) throws Exception {
        String sql = "update account set money = money-? where name = ?and money >= ?";
        int count = template.update(sql, money, out, money);
        return count;
    }

    /**
     * 转入
     * @param in 由哪个帐号转入
     * @param money 转入多少钱
     * @throws Exception
     */
    public int updateForIn(String in, Double money) throws Exception {
        String sql = "update account set money = money+? where name = ?";
        int count = template.update(sql, money, in);
        //throw new Exception("系统故障，资金撤回");
        return count;
    }
}

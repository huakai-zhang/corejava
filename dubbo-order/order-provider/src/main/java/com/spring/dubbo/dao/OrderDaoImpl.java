package com.spring.dubbo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/5/8
 */
@Repository
public class OrderDaoImpl implements OrderDao {

    @Autowired
    JdbcTemplate orderJdbcTemplate;

    @Override
    public void insertOrder() {
        orderJdbcTemplate.execute("insert into `order`(status,price,order_time) values (1,10,now())");
    }
}

package com.spring.mybatis.dao;

import com.spring.model.Author;

public interface AuthorMapper {

    Author selectByPrimaryKey(Integer aid);

}

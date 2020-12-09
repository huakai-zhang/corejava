package com.spring.mybatis.sessiontemplate;

import com.spring.model.User;

import java.util.List;

public interface UserTemplateMapper {

    User selectById(Integer Id);

}
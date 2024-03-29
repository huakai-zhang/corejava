package com.spring.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("用户实体")
public class ApiUser {

    @ApiModelProperty("用户名")
    public String username;

    @ApiModelProperty("密码")
    public String password;
}

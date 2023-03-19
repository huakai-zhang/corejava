package com.spring.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserSearchReq {

    private Long channelId;

    private Long id;

    //@NotBlank(message = "名称不能为空")
    private String randomUser;
}

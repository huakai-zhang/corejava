package com.spring.framework.aop.config;

import lombok.Data;

/**
 * @author 花开不合阳春暮
 * @date 2020/12/7 下午9:25
 */
@Data
public class AopConfig {

    private String pointCut;
    private String aspectBefore;
    private String aspectAfter;
    private String aspectClass;
    private String aspectAfterThrow;
    private String aspectAfterThrowingName;

}

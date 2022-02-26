package com.spring.core.thirdDemo;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

/**
 * @author 春阳
 * @date 2020-12-21 15:58
 */
public class CacheImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        //动态注入bean :自己去实现判断逻辑实现动态配置
        Map<String,Object> attributes=
                importingClassMetadata.getAnnotationAttributes(EnableDefineService.class.getName());

        //返回的是一个固定的CacheService
        return new String[]{CacheService.class.getName()};
    }
}

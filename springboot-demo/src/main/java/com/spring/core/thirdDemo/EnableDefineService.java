package com.spring.core.thirdDemo;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author 春阳
 * @date 2020-12-21 15:57
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({LoggerDefinitionRegistrar.class, CacheImportSelector.class})
public @interface EnableDefineService {
    //配置一些方法
    Class<?>[] exclude() default {};
}

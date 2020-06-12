package com.spring.suport;

import java.lang.annotation.*;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/6/12
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Anonymous {
}

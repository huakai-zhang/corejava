package com.spring;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 花开不合阳春暮
 * @date 2021/1/1 下午1:20
 */
@Target(ElementType.TYPE)// 修饰类/接口
@Retention(RetentionPolicy.RUNTIME)
@Component//被Spring进行扫描
public @interface RpcService {

    Class<?> value(); // 拿到服务的接口

    String version() default ""; // 版本号

}

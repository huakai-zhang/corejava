package com.spring;

import com.spring.v1.HuaKaiRegistry;
import com.spring.v2.SpringConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author 春阳
 * @date 2021-02-04 11:46
 */
public class RpcProvider {
    public static void main(String[] args) {
        //new HuaKaiRegistry(8080).start();
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        applicationContext.start();
    }
}

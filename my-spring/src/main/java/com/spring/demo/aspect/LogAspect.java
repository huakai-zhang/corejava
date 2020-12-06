package com.spring.demo.aspect;

/**
 * @author 花开不合阳春暮
 * @date 2020/11/29 上午10:57
 */
public class LogAspect {


    public void before() {
        //往对象里面记录调用的开始时间
    }

    public void after() {
        //系统当前时间-之前记录的开始时间
    }

    public void afterThrowing() {
        //异常检测，异常信息
    }

}

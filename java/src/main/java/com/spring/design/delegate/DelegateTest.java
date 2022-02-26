package com.spring.design.delegate;

/**
 * 类似于中介的功能（委托机制）
 * 持有被委托人的引用
 * 不关心过程，只关心结果
 * 委派模式的主要目的就是隐藏具体的实现逻辑
 *
 * IOC容器中，有一个Register（为了告诉容器，在这个类被初始化的过程中，需要做很多不同的逻辑
 * ，需要实现多个任务执行者，分别实现各自的功能）
 * 显式指定谁来执行
 * @author Spring Zhang
 * @date 2020/3/5 10:12
 */
public class DelegateTest {
    // 委派模式
    // 两个角色，受托人，委托人(平等关系)
    // 公司里面：项目经理，普通员工（法律上平等，工作关系，各自的职责不一样）
    // 项目经理（委托人）：主要是安排任务
    // 普通员工（受托人）：执行任务

    public static void main(String[] args) {
        Dispatcher dispatcher = new Dispatcher(new TargetA());
        // 看上去是项目经理在干活
        // 实际上干活的是普通员工
        // 典型的是，干活的是你，功劳是我的
        dispatcher.doing();
    }
}
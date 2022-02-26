package com.spring;

import com.alibaba.csp.sentinel.cluster.ClusterStateManager;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 花开不合阳春暮
 * @date 2021/1/23 上午11:33
 */
@SpringBootApplication
public class DubboSpringBootApplication {
    public static void main(String[] args) {
        initDegradeRules();
        //表示当前的节点是集群客户端
        //ClusterStateManager.applyState(ClusterStateManager.CLUSTER_CLIENT);
        SpringApplication.run(DubboSpringBootApplication.class, args);
    }

    //初始化规则
    private static void initFlowRules() {
        //限流规则的集合
        List<FlowRule> rules = new ArrayList<>();
        FlowRule flowRule = new FlowRule();
        //资源(方法名称、接口）
        flowRule.setResource("com.spring.service.ISayHelloService:sayHello()");
        //限流阈值 qps=10
        flowRule.setCount(10);
        //限流阈值类型（QPS 或并发线程数）
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        //流量控制手段（直接拒绝、Warm Up、匀速排队）
        flowRule.setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_DEFAULT);
        //流控针对的调用来源，若为 default 则不区分调用来源
        flowRule.setLimitApp("dubbo-springboot-client");
        rules.add(flowRule);
        FlowRuleManager.loadRules(rules);
    }

    public static void initDegradeRules() {
        List<DegradeRule> rules=new ArrayList<>();
        DegradeRule rule=new DegradeRule();
        // 指定被保护的资源
        rule.setResource("com.spring.service.ISayHelloService");
        // 下面这个配置的意思是，当1s内持续进入2个请求，平均响应时间都超过count(10ms)
        // 那么在接下来的timewindow(10s)内，对这个方法的调用都会自动熔断，抛出异常:degradeException
        rule.setMinRequestAmount(2);
        //阈值
        rule.setCount(10);
        //降级模式, RT（平均响应时间）、异常比例(DEGRADE_GRADE_EXCEPTION_RATIO)/异常数量
        rule.setGrade(RuleConstant.DEGRADE_GRADE_RT);
        //降级的时间单位, 单位为s
        rule.setTimeWindow(10);
        rules.add(rule);
        DegradeRuleManager.loadRules(rules);
    }
}

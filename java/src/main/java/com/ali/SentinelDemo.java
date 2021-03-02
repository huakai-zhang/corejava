package com.ali;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author 花开不合阳春暮
 * @date 2021/3/1 下午9:44
 */
public class SentinelDemo {

    /**
     * 定义规则
     */
    public static void initFlowRules() {
        // 限流规则的集合
        List<FlowRule> ruleList = new ArrayList<>();
        FlowRule flowRule = new FlowRule();
        // 资源（方法名称、接口）
        flowRule.setResource("doTest");
        // 限流的阈值类型
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        flowRule.setCount(10);
        ruleList.add(flowRule);
        FlowRuleManager.loadRules(ruleList);
    }

    public static void main(String[] args) {
        initFlowRules();
        while (true) {
            // 定义资源
            Entry entry = null;
            try {
                Thread.sleep(new Random().nextInt(200));
                entry = SphU.entry("doTest");
                // 把需要控制流量的代码用 Sentinel API SphU.entry("doTest") 和 entry.exit() 包围起来即可
                // 这端代码作为资源，用 API 包围起来（埋点）
                /*业务逻辑 - 开始*/
                System.out.println("Hello Sentinel");
                /*业务逻辑 - 结束*/
            } catch (Exception e) {
                // 如果被限流了，会抛出异常
                /*流控逻辑处理 - 开始*/
                System.out.println("block!");
                /*流控逻辑处理 - 结束*/
            } finally {
                if (entry != null) {
                    // 释放
                    entry.exit();
                }
            }
        }
    }
}

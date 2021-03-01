package com.ali;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 花开不合阳春暮
 * @date 2021/3/1 下午9:44
 */
public class SentinelDemo {

    /**
     * 初始化规则
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
            Entry entry = null;
            try {
                entry = SphU.entry("doTest");
                System.out.println("Hello Sentinel");
                Thread.sleep(99);
                // 如果被限流了，会抛出异常
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (entry != null) {
                    // 释放
                    entry.exit();
                }
            }
        }
    }
}

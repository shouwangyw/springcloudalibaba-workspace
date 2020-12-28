package com.yw.sca;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangwei
 * @date 2020-12-12 19:13
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Consumer068080 {
    public static void main(String[] args) {
        SpringApplication.run(Consumer068080.class, args);
        initFlowRule();
    }

    private static void initFlowRule() {
        List<FlowRule> rules = new ArrayList<>();
        rules.add(qpsFlowRule());
//        rules.add(threadFlowRule());
//        rules.add(qpsRelateFowRule());
//        rules.add(qpsChaniFlowRule());
//        rules.add(qpsWarmUpFlowRule());
//        rules.add(qpsQueueFlowRule());
        FlowRuleManager.loadRules(rules);
    }

    private static FlowRule qpsFlowRule() {
        FlowRule rule = new FlowRule();
        rule.setRefResource("qpsFlowRule");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setCount(1);
        // 值为default，表示对请求来源不做限定
        rule.setLimitApp("default");
        return rule;
    }

    private static FlowRule threadFlowRule() {
        FlowRule rule = new FlowRule();
        rule.setResource("threadFlowRule");
        rule.setGrade(RuleConstant.FLOW_GRADE_THREAD);
        rule.setCount(20);
        rule.setLimitApp("default");
        return rule;
    }

    private static FlowRule qpsRelateFowRule() {
        FlowRule rule = new FlowRule();
        rule.setResource("qpsRelateFowRule");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setCount(1);
        rule.setStrategy(RuleConstant.STRATEGY_RELATE);
        rule.setRefResource("/list");
        rule.setLimitApp("default");
        return rule;
    }

    private static FlowRule qpsChaniFlowRule() {
        FlowRule rule = new FlowRule();
        rule.setResource("qpsChaniFlowRule");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setCount(1);
        rule.setStrategy(RuleConstant.STRATEGY_CHAIN);
        rule.setRefResource("/provider/depart/list");
        rule.setLimitApp("default");
        return rule;
    }

    private static FlowRule qpsWarmUpFlowRule() {
        FlowRule rule = new FlowRule();
        rule.setResource("qpsWarmUpFlowRule");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setLimitApp("default");
        rule.setCount(20);
        rule.setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_WARM_UP);
        rule.setWarmUpPeriodSec(5);
        return rule;
    }

    private static FlowRule qpsQueueFlowRule() {
        FlowRule rule = new FlowRule();
        rule.setResource("qpsQueueFlowRule");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setLimitApp("default");
        rule.setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_RATE_LIMITER);
        rule.setCount(20);
        rule.setMaxQueueingTimeMs(20 * 1000);
        return rule;
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

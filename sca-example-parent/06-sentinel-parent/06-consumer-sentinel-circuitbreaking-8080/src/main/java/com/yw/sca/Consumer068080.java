package com.yw.sca;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
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
        // 初始化规则
        initRule();
    }

    private static void initRule() {
        List<DegradeRule> rules = new ArrayList<>();
        rules.add(slowRequestDegradeRule());
        DegradeRuleManager.loadRules(rules);
    }

    private static DegradeRule slowRequestDegradeRule() {
        // 创建一个降级规则实例
        DegradeRule rule = new DegradeRule();
        // 指定该规则要应用的资源名称
        rule.setResource("findById");
        // 指定熔断规则为：慢调用比例
        rule.setGrade(RuleConstant.DEGRADE_GRADE_RT);
        // 设置阈值：RT的时间，单位毫秒。若一个请求获取到响应时间超出该值，则会将该请求统计为"慢调用"
        rule.setCount(200);
        // 设置熔断窗口大小，单位秒
        rule.setTimeWindow(30);
        // 设置最小请求数量
        rule.setMinRequestAmount(5);
        // 设置发生慢调用的比例
        rule.setSlowRatioThreshold(0.5);
        return rule;
    }

    private static DegradeRule exceptionRatioDegradeRule() {
        DegradeRule rule = new DegradeRule();
        rule.setResource("findById");
        // 指定熔断规则为：异常比例
        rule.setGrade(RuleConstant.DEGRADE_GRADE_EXCEPTION_RATIO);
        // 设置阈值：发生熔断的异常请求比例
        rule.setCount(0.5);
        rule.setTimeWindow(60);
        rule.setMinRequestAmount(5);
        return rule;
    }

    private static DegradeRule exceptionCountDegradeRule() {
        DegradeRule rule = new DegradeRule();
        rule.setResource("findById");
        // 指定熔断规则为：异常数
        rule.setGrade(RuleConstant.DEGRADE_GRADE_EXCEPTION_COUNT);
        // 设置阈值：发生熔断的异常请求比例
        rule.setCount(5);
        rule.setTimeWindow(60);
        rule.setMinRequestAmount(5);
        return rule;
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

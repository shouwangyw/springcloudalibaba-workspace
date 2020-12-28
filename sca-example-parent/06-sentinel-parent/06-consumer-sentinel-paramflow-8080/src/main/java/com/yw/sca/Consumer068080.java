package com.yw.sca;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowItem;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRuleManager;
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
        initRule();
    }

    private static void initRule() {
        List<ParamFlowRule> rules = new ArrayList<>();
        rules.add(paramFlowRule());
        ParamFlowRuleManager.loadRules(rules);
    }

    private static ParamFlowRule paramFlowRule() {
        ParamFlowRule rule = new ParamFlowRule();
        rule.setResource("/complux");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setLimitApp("default");
        rule.setCount(2);
        rule.setParamIdx(1);
        rule.setDurationInSec(10);
        List<ParamFlowItem> items = new ArrayList<>();
        items.add(nameParamItem("human", 100));
        items.add(nameParamItem("test", 10));
        rule.setParamFlowItemList(items);
        return rule;
    }

    private static ParamFlowItem nameParamItem(String paramValue, int count) {
        ParamFlowItem item = new ParamFlowItem();
        item.setClassType(String.class.getName());
        item.setObject(String.valueOf(paramValue));
        item.setCount(count);
        return item;
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

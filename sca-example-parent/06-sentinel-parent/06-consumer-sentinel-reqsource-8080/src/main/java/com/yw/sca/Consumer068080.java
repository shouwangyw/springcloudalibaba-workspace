package com.yw.sca;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRule;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRuleManager;
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
        List<AuthorityRule> rules = new ArrayList<>();
        rules.add(reqSourceRule());
        AuthorityRuleManager.loadRules(rules);
    }

    private static AuthorityRule reqSourceRule() {
        AuthorityRule rule = new AuthorityRule();
        rule.setResource("reqSourceRule");
        rule.setStrategy(RuleConstant.AUTHORITY_WHITE);
        rule.setLimitApp("serviceA,serviceB");
        return rule;
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

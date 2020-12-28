package com.yw.sca.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangwei
 * @date 2020-12-21 23:24
 */
@Configuration
public class LoadBalanceConfig {
    // 当代码与配置文件中均设置了负载均衡策略时，代码中的优先级高
//    @Bean
//    public IRule loadBalanceRule() {
//        return new RandomRule();
//    }

    @Bean
    public IRule loadBalancer() {
        List<Integer> ports = new ArrayList<>();
        ports.add(8082);
        return new CustomRule(ports);
    }
}

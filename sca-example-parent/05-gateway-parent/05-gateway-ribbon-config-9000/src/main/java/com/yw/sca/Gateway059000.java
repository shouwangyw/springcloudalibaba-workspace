package com.yw.sca;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author yangwei
 * @date 2020-12-12 19:13
 */
@SpringBootApplication
public class Gateway059000 {
    public static void main(String[] args) {
        SpringApplication.run(Gateway059000.class, args);
    }
    @Bean
    public IRule loadBalanceRule() {
        return new RandomRule();
    }
}

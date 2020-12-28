package com.yw.sca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Mono;

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
    public KeyResolver keyResolver() {
        return exchange -> Mono.just(exchange.getRequest()
                .getRemoteAddress().getHostName());
    }
}

package com.yw.sca.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author yangwei
 * @date 2020-12-24 22:48
 */
@Slf4j
public class TwoGatewayFilter implements GatewayFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // pre-filter
        log.info("pre-filter-222");
        return chain.filter(exchange).then(
                // post-filter
                Mono.fromRunnable(() -> log.info("post-filter-222 " + System.currentTimeMillis()))
        );
    }
}

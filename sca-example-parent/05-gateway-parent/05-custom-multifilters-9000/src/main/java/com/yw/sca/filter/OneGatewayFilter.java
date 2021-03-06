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
public class OneGatewayFilter implements GatewayFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // pre-filter
        long startTime = System.currentTimeMillis();
        log.info("pre-filter-111 " + startTime);
        exchange.getAttributes().put("startTime", startTime);

        return chain.filter(exchange).then(
                // post-filter，使用一个Runnable任务构建一个Mono
                Mono.fromRunnable(() -> {
                    log.info("post-filter-111");
                    long startTimeAttr = (long) exchange.getAttributes().get("startTime");
                    long elapsedTime = System.currentTimeMillis() - startTimeAttr;
                    log.info("该过滤器执行用时 = " + elapsedTime);
                })
        );
    }
}

package com.yw.sca.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author yangwei
 * @date 2020-12-24 22:48
 */
public class AddHeaderGatewayFilter implements GatewayFilter {
    /**
     * Mono：包含0个或1个元素的异步序列
     * Flux：包含0个或多个元素的异步序列
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 向请求中添加指定的header
        ServerHttpRequest httpRequest = exchange.getRequest()
                .mutate()   // 变更
                .header("X-Request-Red", "blue")
                .build();
        // 将变更过的请求写入到exchange
        ServerWebExchange webExchange = exchange.mutate()
                .request(httpRequest)
                .build();
        // 将变更过的exchange向下传递
        return chain.filter(webExchange);
    }
}

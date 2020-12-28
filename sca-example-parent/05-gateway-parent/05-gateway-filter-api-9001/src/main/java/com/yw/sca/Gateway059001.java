package com.yw.sca;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import reactor.core.publisher.Mono;

/**
 * @author yangwei
 * @date 2020-12-12 19:13
 */
@Slf4j
@SpringBootApplication
public class Gateway059001 {
    public static void main(String[] args) {
        SpringApplication.run(Gateway059001.class, args);
    }

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
//                .route("addRequestHeader_filter", ps -> ps.path("/**")
//                .route("addRequestParameter_filter", ps -> ps.path("/**")
//                .route("addResponseHeader_filter", ps -> ps.path("/**")
//                .route("prefixPath_filter", ps -> ps.path("/**")
//                .route("stripPrefix_filter", ps -> ps.path("/**")
//                .route("rewritePath_filter", ps -> ps.path("/**")
                .route("circuitBreaker_filter", ps -> ps.path("/**")
//                        .filters(fs -> fs.addRequestHeader("X-Request-Red", "Red"))
//                        .filters(fs -> fs.addRequestParameter("color", "Red"))
//                        .filters(fs -> fs.addResponseHeader("X-Response-Color", "Red"))
//                        .filters(fs -> fs.prefixPath("/info"))
//                        .filters(fs -> fs.stripPrefix(2))
//                        .filters(fs -> fs.rewritePath("/red(?<segment>/?.*)", "${segment}"))
                        .filters(fs -> fs.circuitBreaker(config -> {
                                    config.setName("myCircuitBreaker");
                                    config.setFallbackUri("forward:/fallback");
                                }))
                        .uri("http://localhost:8080")
                ).build();
    }

    @Bean
    @Order(-1)
    public GlobalFilter globalFilter() {
        return (exchange, chain) -> {
            log.info("first pre filter");
            return chain.filter(exchange).then(
                    Mono.fromRunnable(() -> log.info("third post filter")));
        };
    }
}

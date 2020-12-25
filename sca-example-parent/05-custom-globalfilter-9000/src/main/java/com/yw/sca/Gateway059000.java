package com.yw.sca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
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
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("custom_filter_route", ps -> ps.path("/**")
                        .uri("http://localhost:8080")
                ).build();
    }
}

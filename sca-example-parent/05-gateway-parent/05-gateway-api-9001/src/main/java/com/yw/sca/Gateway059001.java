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
public class Gateway059001 {
    public static void main(String[] args) {
        SpringApplication.run(Gateway059001.class, args);
    }

//    @Bean
//    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route(ps -> ps.path("/**").uri("https://www.baidu.com").id("baidu_route"))
//                .build();
//    }

//    @Bean
//    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
//        ZonedDateTime dateTime = LocalDateTime.now().minusDays(5)
//                .atZone(ZoneId.systemDefault());
//
//        return builder.routes()
//                .route(ps -> ps.after(dateTime)
//                .route(ps -> ps.before(dateTime)
//                        .uri("https://www.baidu.com")
//                        .id("after_route"))
//                        .id("before_route"))
//                .build();
//    }

//    @Bean
//    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
//        ZonedDateTime minusTime = LocalDateTime.now().minusDays(5)
//                .atZone(ZoneId.systemDefault());
//        ZonedDateTime plusTime = LocalDateTime.now().plusDays(3)
//                .atZone(ZoneId.systemDefault());
//
//        return builder.routes()
//                .route("between_route", ps -> ps.between(minusTime, plusTime)
//                        .uri("https://www.baidu.com"))
//                .build();
//    }

//    @Bean
//    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
////                .route("cookie_route", ps -> ps.cookie("chocolate", "regex")
////                .route("header_route", ps -> ps.header("X-Request-Id", "\\d+")
////                  .route("host_route", ps -> ps.host("myhost:9000")
////                .route("method_route", ps -> ps.method("POST")
////                .route("provider_route", ps -> ps.path("/provider/**").uri("http://localhost:8081"))
////                .route("consumer_route", ps -> ps.path("/consumer/**").uri("http://localhost:8080"))
//                .route("remoteAddr_route", ps -> ps.remoteAddr("192.168.3.1/24")
//                        .uri("https://www.baidu.com "))
//                .build();
//    }

//    @Bean
//    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route("query_route", ps -> ps.query("color", "gr.+")
//                        .and()
//                        .query("size", "5")
//                        .uri("http://localhost:8081"))
//                .build();
//    }

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("weight_baidu_route", ps -> ps.weight("search", 8)
                        .uri("https://www.baidu.com"))
                .route("weight_360_route", ps -> ps.weight("search", 2)
                        .uri("https://www.so.com"))
                .build();
    }
}

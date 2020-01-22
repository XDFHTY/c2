package com.cj.sgateway.controller;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

//    @Bean
//    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route(p -> p
//                        .path("/s-admin/**")
//                        .filters(f -> f.stripPrefix(1))
//                        .uri("http://127.0.0.1:9021"))
//                .route(p -> p
//                        .path("/s-user/**")
//                        .filters(f -> f.stripPrefix(1))
//                        .uri("http://127.0.0.1:9022"))
//                .build();
//    }

}

package com.cj.sgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SGatewayApplication.class, args);
    }


}

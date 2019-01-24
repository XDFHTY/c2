package com.cj.szuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy  //开启zuul
@EnableEurekaClient
public class SZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(SZuulApplication.class, args);
	}



}


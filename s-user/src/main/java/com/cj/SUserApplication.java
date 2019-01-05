package com.cj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(SUserApplication.class, args);
	}

}


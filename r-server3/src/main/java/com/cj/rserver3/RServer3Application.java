package com.cj.rserver3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RServer3Application {

	public static void main(String[] args) {
		SpringApplication.run(RServer3Application.class, args);
	}

}


package com.cj.rserver2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RServer2Application {

	public static void main(String[] args) {
		SpringApplication.run(RServer2Application.class, args);
	}

}


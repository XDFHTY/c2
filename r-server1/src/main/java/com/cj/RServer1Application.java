package com.cj;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RServer1Application {

	public static void main(String[] args) {
		SpringApplication.run(RServer1Application.class, args);
	}

}


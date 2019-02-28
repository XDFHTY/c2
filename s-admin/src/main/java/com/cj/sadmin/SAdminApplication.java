package com.cj.sadmin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient

@MapperScan({"com.cj.common.mapper","com.cj.sadmin.mapper"})
@ComponentScan(basePackages = {"com.cj"})
public class SAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(SAdminApplication.class, args);
	}

}


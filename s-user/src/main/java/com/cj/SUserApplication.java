package com.cj;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@EnableDistributedTransaction
@EnableFeignClients//打开Feign注解

@MapperScan({"com.cj.common.mapper","com.cj.suser.mapper"})
@ComponentScan(basePackages = {"com.cj"})
public class SUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(SUserApplication.class, args);
	}

}


package com.cj.sadmin;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableEurekaClient

@MapperScan({"com.cj.common.mapper","com.cj.sadmin.mapper"})
@ComponentScan(basePackages = {"com.cj"})
//@EnableDistributedTransaction
//@EnableTransactionManagement
public class SAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(SAdminApplication.class, args);
	}

}


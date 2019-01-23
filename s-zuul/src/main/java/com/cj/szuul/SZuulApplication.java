package com.cj.szuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@SpringBootApplication
@EnableZuulProxy  //开启zuul
@EnableEurekaClient
public class SZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(SZuulApplication.class, args);
	}

//	//继续寻找配置文件
//	@Bean
//	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
//		PropertySourcesPlaceholderConfigurer c = new PropertySourcesPlaceholderConfigurer();
//		c.setIgnoreUnresolvablePlaceholders(true);
//		return c;
//	}

}


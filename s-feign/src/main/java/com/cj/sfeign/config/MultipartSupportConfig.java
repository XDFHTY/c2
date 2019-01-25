package com.cj.sfeign.config;


import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.context.annotation.Bean;

/**
 * 引用配置类MultipartSupportConfig.并且实例化
 */
public class MultipartSupportConfig {
        @Bean
        public Encoder feignFormEncoder()  {
            return new FeignSpringFormEncoder();
        }
}

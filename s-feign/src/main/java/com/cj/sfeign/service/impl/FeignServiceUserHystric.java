package com.cj.sfeign.service.impl;

import com.cj.sfeign.service.FeignServiceUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * FeignServiceUser 断路器
 */

@Component
public class FeignServiceUserHystric implements FeignServiceUser {

    @Value("${spring.application.name}")
    private String serverName;

    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry "+name+",(s-user)服务异常,i am from "+serverName;
    }
}

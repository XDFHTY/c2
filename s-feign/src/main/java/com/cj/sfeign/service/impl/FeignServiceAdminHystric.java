package com.cj.sfeign.service.impl;

import com.cj.sfeign.service.FeignServiceAdmin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FeignServiceAdminHystric implements FeignServiceAdmin {

    @Value("${spring.application.name}")
    private String serverName;

    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry "+name+",(s-admin)服务异常,i am from "+serverName;
    }
}

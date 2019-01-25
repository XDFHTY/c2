package com.cj.sfeign.service.impl;

import com.cj.sfeign.service.FeignAdminService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FeignAdminServiceHystric implements FeignAdminService {

    @Value("${spring.application.name}")
    private String serverName;

    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry "+name+",(s-admin)服务异常,i am from "+serverName;
    }
}

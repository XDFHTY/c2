package com.cj.sfeign.service.impl;

import com.cj.sfeign.service.FeignFileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FeignFileServiceHystric implements FeignFileService {

    @Value("${spring.application.name}")
    private String serverName;

    @Override
    public String sayHiFromClientOne(MultipartFile[] files) {
        return "sorry "+",(s-file)服务异常,i am from "+serverName;
    }
}

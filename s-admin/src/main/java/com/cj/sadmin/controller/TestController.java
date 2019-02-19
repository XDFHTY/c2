package com.cj.sadmin.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin")
@RefreshScope
public class TestController {

    @Value("${server.port}")
    String port;

    @Value("${fo}")
    private String fo;


    @GetMapping("/hi")
    public String home() throws Exception {

        return "(admin)hi ,i am from port:" +port;
    }


    @GetMapping("/fo")
    public String getFo() {
        return fo;
    }
}

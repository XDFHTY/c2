package com.cj.sadmin.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
@RefreshScope
public class TestController {

    @Value("${server.port}")
    String port;


    @GetMapping("/hi")
    public String home() throws Exception {

        return "(admin)hi ,i am from port:" +port;
    }


//    @Value("${fo}")
//    private String fo;
//
//    @GetMapping("/fo")
//    public String getFo() {
//        return fo;
//    }
}

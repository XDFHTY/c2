package com.cj.sadmin.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/admin-api")
public class TestController {

    @Value("${server.port}")
    String port;


    @GetMapping("/hi")
    public String home() throws Exception {

        return "(admin)hi ,i am from port:" +port;
    }


}

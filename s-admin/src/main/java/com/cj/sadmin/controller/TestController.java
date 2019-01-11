package com.cj.sadmin.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin-api")
public class TestController {

    @Value("${server.port}")
    String port;


    @GetMapping("/hi")
    public String home(@RequestParam String name) throws Exception {

        return "(admin)hi "+name+",i am from port:" +port;
    }
}

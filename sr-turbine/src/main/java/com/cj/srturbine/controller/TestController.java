package com.cj.srturbine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {


    @GetMapping("/")
    public String toPage(){

        return "redirect:/hystrix/monitor?stream=http://127.0.0.1:9007/turbine.stream";
    }
}

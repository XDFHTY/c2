package com.cj.sfeign.controller;

import com.cj.sfeign.service.FeignUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user-api")
public class UserController {

    @Resource
    private FeignUserService feignUserService;


    @GetMapping(value = "/hi")
    public String sayHi(@RequestParam String name){
        return feignUserService.sayHiFromClientOne(name);
    }
}

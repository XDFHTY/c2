package com.cj.sfeign.controller;

import com.cj.sfeign.service.FeignAdminService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin-api")
public class AdminController {

    @Resource
    private FeignAdminService feignAdminService;


    @GetMapping(value = "/hi")
    public String sayHi(@RequestParam String name){
        return feignAdminService.sayHiFromClientOne(name);
    }
}

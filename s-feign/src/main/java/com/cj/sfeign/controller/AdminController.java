package com.cj.sfeign.controller;

import com.cj.sfeign.service.FeignServiceAdmin;
import com.cj.sfeign.service.FeignServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin-api")
public class AdminController {

    @Autowired
    private FeignServiceAdmin feignServiceAdmin;


    @GetMapping(value = "/hi")
    public String sayHi(@RequestParam String name){
        return feignServiceAdmin.sayHiFromClientOne(name);
    }
}

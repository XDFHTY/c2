package com.cj.szuul.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigController {

    @Value("${foo}")
    private String foo;

    @Value("${foo.name}")
    private String fooName;


    @RequestMapping(value = "/foo")
    public String foo(){
        return foo;
    }
    @RequestMapping(value = "/fooName")
    public String fooName(){
        return fooName;
    }

}

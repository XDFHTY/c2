package com.cj.szuul.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RefreshScope //从配置中心动态获取配置文件
@Slf4j
public class TestController {

    @Value("${server.port}")
    private String foo;

//    @Value("${foo.name}")
//    private String fooName;


//    @Resource
//    private RedisTemplate redisTemplate;




    @GetMapping(value = "/foo")
    public String foo(){
        return "foo="+foo+",fooname=";
    }



}

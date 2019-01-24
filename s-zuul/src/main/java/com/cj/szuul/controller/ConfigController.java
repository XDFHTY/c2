package com.cj.szuul.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope //从配置中心动态获取配置文件
public class ConfigController {

    @Value("${foo}")
    private String foo;

    @Value("${foo.name}")
    private String fooName;



    @RequestMapping(value = "/foo")
    public String foo(){
        return "foo="+foo+",fooname="+fooName;
    }


}

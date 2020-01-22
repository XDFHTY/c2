package com.cj.suser.controller;


import com.cj.core.domain.ApiResult;
import com.cj.core.domain.ResultUtil;
import com.cj.suser.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TestController {

    @Value("${server.port}")
    String port;

    @Value("${fo2}")
    private String fo2;

    @GetMapping("/hi")
    public String home(@RequestParam String name) throws Exception {

        return "(user)hi "+name+",i am from port:" +port;
    }

    @Autowired
    private TestService testService;

    @GetMapping("sutx")
    public ApiResult TestTx(){
        int i = testService.testTx();
        ApiResult apiResult;
        if (i>0){
            apiResult = ApiResult.SUCCESS();
            apiResult.setData(fo2);
        }else {
            apiResult = ApiResult.FAIL();
            apiResult.setData(fo2);
        }
        return apiResult;
    }

    @GetMapping("/fo")
    public ApiResult fo(){

        return ResultUtil.result(fo2);
    }

    @PostMapping("/test/user/login")
    public ApiResult login(){
        Map map = new HashMap();

        map.put("token","AAASSSQQQWWW");

        return ResultUtil.result(map);
    }

}

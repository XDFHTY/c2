package com.cj.suser.controller;


import com.cj.core.domain.ApiResult;
import com.cj.suser.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @Value("${server.port}")
    String port;


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
        }else {
            apiResult = ApiResult.FAIL();
        }
        return apiResult;
    }

}

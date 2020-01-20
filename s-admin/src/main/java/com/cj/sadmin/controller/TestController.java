package com.cj.sadmin.controller;


import com.cj.common.exception.UserException;
import com.cj.core.domain.ApiResult;
import com.cj.sadmin.mapper.AdminMapper;
import com.cj.sadmin.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/api",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//@RefreshScope
public class TestController {

    @Value("${server.port}")
    String port;


    @GetMapping("/hi")
    public String home() throws Exception {

        return "(admin)hi ,i am from port:" +port;
    }

    @Autowired
    private TestService testService;


    @GetMapping(value = "/satx")
    public ApiResult testTx(){

        List list = testService.testTx();

        ApiResult apiResult = ApiResult.SUCCESS();
        apiResult.setData(list);
        if (true){
            apiResult = ApiResult.FAIL();
            throw new UserException(apiResult);

        }
        return apiResult;

    }

    @Value("${fo}")
    private String fo;

    @GetMapping("/fo")
    public String getFo() {
        return fo+" :    "+port;
    }
}

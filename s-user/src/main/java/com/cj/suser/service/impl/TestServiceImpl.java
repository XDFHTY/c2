package com.cj.suser.service.impl;

import com.cj.core.domain.ApiResult;
import com.cj.suser.mapper.AdminMapper;
import com.cj.suser.service.TestService;
import com.cj.suser.call.UserCallAdminService;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
@Transactional
@RefreshScope
public class TestServiceImpl implements TestService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired(required = false)
    private AdminMapper adminMapper;

    @Resource
    private UserCallAdminService userCallAdminService;

    Gson gson = new Gson();
    @LcnTransaction
    @Override
    public int testTx() {

        int i = adminMapper.testTX();

//        String token = "";
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
//        headers.add("token", token);
//        String url = "http://s-admin/api/satx";
//        String json = "222";
//
//        String s = restTemplate.getForObject(
//                url,
//                 String.class,
//        new HttpEntity<String>(json, headers)
//        );
//
//        ApiResult apiResult = gson.fromJson(s,ApiResult.class);
        ApiResult apiResult = userCallAdminService.getSatx();
        System.out.println(apiResult);
        if (apiResult == null){
            throw new RuntimeException();
        }
        return i;

    }
}

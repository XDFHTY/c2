package com.cj.suser.service.impl;

import com.cj.core.domain.ApiResult;
import com.cj.core.domain.MemoryData;
import com.cj.suser.mapper.AdminMapper;
import com.cj.suser.service.TestService;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Transactional
@RefreshScope
public class TestServiceImpl implements TestService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired(required = false)
    private AdminMapper adminMapper;

    Gson gson = new Gson();
    @LcnTransaction
    @Override
    public int testTx() {

        int i = adminMapper.testTX();

        String token = "";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add("token", token);
        String url = "http://s-admin/api/satx";
        String json = "222";

        String s = restTemplate.getForObject(
                url,
                 String.class,
        new HttpEntity<String>(json, headers)
        );

        ApiResult apiResult = gson.fromJson(s,ApiResult.class);
        System.out.println(apiResult);
        return i;

    }
}

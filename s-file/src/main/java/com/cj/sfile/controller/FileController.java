package com.cj.sfile.controller;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping("/file-api")
@RefreshScope
public class FileController {


    @Resource
    private RestTemplate restTemplate;

    @PostMapping(value = "/uploads",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploads(MultipartFile[] files){


        System.out.println(files.length);

        ResponseEntity<String> s = restTemplate.getForEntity("http://127.0.0.1:9201/admin-api/hi",String.class);

        return files.length+"==="+s.getBody();

    }
}

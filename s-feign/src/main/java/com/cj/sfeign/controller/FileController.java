package com.cj.sfeign.controller;

import com.cj.sfeign.service.FeignFileService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping("/file-api")
public class FileController {

    @Resource
    private FeignFileService feignFileService;


    @PostMapping(value = "/uploads",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String sayHi(MultipartFile[] files){
        System.out.println(files.length);
        return feignFileService.sayHiFromClientOne(files);
    }
}

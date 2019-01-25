package com.cj.sfeign.service;

import com.cj.sfeign.config.MultipartSupportConfig;
import com.cj.sfeign.service.impl.FeignFileServiceHystric;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(value = "s-file",path = "/file-api",fallback = FeignFileServiceHystric.class,configuration = MultipartSupportConfig.class)
public interface FeignFileService {

    @PostMapping(value = "/uploads",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String sayHiFromClientOne(@RequestPart(value = "files") MultipartFile[] files);
}


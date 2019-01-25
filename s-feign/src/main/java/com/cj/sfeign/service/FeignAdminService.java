package com.cj.sfeign.service;

import com.cj.sfeign.service.impl.FeignAdminServiceHystric;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "s-admin",fallback = FeignAdminServiceHystric.class)
public interface FeignAdminService {


    @GetMapping(value = "/admin-api/hi")
    String sayHiFromClientOne(@RequestParam(value = "name") String name);
}

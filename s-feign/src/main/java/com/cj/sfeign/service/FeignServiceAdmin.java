package com.cj.sfeign.service;

import com.cj.sfeign.service.impl.FeignServiceAdminHystric;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "s-admin",fallback = FeignServiceAdminHystric.class)
public interface FeignServiceAdmin {


    @GetMapping(value = "/admin-api/hi")
    String sayHiFromClientOne(@RequestParam(value = "name") String name);
}

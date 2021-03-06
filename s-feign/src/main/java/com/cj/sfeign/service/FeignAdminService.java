package com.cj.sfeign.service;

import com.cj.sfeign.service.impl.FeignAdminServiceHystric;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "s-admin",fallback = FeignAdminServiceHystric.class)
public interface FeignAdminService {


    @GetMapping(value = "/api/admin/hi")
    String sayHiFromClientOne(@RequestParam(value = "name") String name);
}

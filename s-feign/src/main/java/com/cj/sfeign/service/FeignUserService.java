package com.cj.sfeign.service;


import com.cj.sfeign.service.impl.FeignUserServiceHystric;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "s-user",fallback = FeignUserServiceHystric.class)
public interface FeignUserService {


    @GetMapping(value = "/api/user/hi")
    String sayHiFromClientOne(@RequestParam(value = "name") String name);
}

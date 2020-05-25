package com.cj.suser.call;

import com.cj.core.domain.ApiResult;
import com.cj.suser.call.fallback.UserCallAdminServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "s-admin",fallback = UserCallAdminServiceFallBack.class)
@Configuration
public interface UserCallAdminService {

    @GetMapping(value = "/api/satx",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ApiResult getSatx();
}

package com.cj.suser.call.fallback;

import com.cj.core.domain.ApiResult;
import com.cj.suser.call.UserCallAdminService;
import org.springframework.stereotype.Component;

@Component
public class UserCallAdminServiceFallBack implements UserCallAdminService {
    @Override
    public ApiResult getSatx() {
        System.out.println("==>>suser-->sadmin");
        return null;
    }
}

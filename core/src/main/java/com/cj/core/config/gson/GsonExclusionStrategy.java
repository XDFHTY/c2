package com.cj.core.config.gson;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import org.apache.catalina.filters.RemoteIpFilter;
//import org.springframework.security.authentication.AnonymousAuthenticationToken;
//import org.springframework.security.web.header.HeaderWriterFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GsonExclusionStrategy implements ExclusionStrategy {
    String[] strs = {"handler","request","response"};
    @Override
    public boolean shouldSkipField(FieldAttributes fieldAttributes) {
        String reqname = fieldAttributes.getName();
        boolean b = false;
        for (String str : strs){
            b = reqname.contains(str);
        }

        return b;
    }

    @Override
    public boolean shouldSkipClass(Class<?> aClass) {
        if(
                aClass == HttpServletRequest.class |
                aClass == HttpServletResponse.class |
//                aClass == AnonymousAuthenticationToken.class |
                aClass == RemoteIpFilter.XForwardedRequest.class
//                aClass == HeaderWriterFilter.class

        ){
            return true;
        }
        return false;
    }

}
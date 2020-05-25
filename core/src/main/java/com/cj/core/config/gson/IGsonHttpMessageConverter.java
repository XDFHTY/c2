package com.cj.core.config.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import springfox.documentation.spring.web.json.Json;


@Configuration
public class IGsonHttpMessageConverter extends GsonHttpMessageConverter {


    public IGsonHttpMessageConverter() {
        super();
        this.setGson(gson());
    }

    Gson gson() {
        final GsonBuilder builder = new GsonBuilder()

                .setLenient()// json宽松
                .enableComplexMapKeySerialization()//支持Map的key为复杂对象的形式
                .serializeNulls() //智能null
//                .setDateFormat(DateUtil.YYYY_MM_DDHHMMSS)
//            .setPrettyPrinting()// 调整格式 ，使对齐
//            .disableHtmlEscaping() //默认是GSON把HTML 转义的
                .registerTypeAdapter(Json.class, new SpringfoxJsonToGsonSerialer())
                .registerTypeAdapterFactory(new DateNullAdapterFactory<>()) //支持 date=''
                .setExclusionStrategies(new GsonExclusionStrategy()) //自定义排除转json的字段或者类名
//            .excludeFieldsWithoutExposeAnnotation()//启用	@Expose
                ;
        return builder.create();
    }


}

package com.cj.core.config.gson;

import com.google.gson.Gson;

public class GsonUtil {

    public static Gson gson = new IGsonHttpMessageConverter().gson();
//            new GsonBuilder()
//
////            .setExclusionStrategies(new ExclusionStrategy(){
////                @Override
////                public boolean shouldSkipField(FieldAttributes fieldAttributes) {
////                    return fieldAttributes.getName().contains("handler");
////                }
////
////                @Override
////                public boolean shouldSkipClass(Class<?> aClass) {
////                    return false;
////                }
////            })
//
//            .setLenient()// json宽松
//            .enableComplexMapKeySerialization()//支持Map的key为复杂对象的形式
//            .serializeNulls() //智能null
//            .setDateFormat(DateUtil.YYYY_MM_DDHHMMSS)
////            .setPrettyPrinting()// 调整格式 ，使对齐
////            .disableHtmlEscaping() //默认是GSON把HTML 转义的
//            .registerTypeAdapter(Json.class,new SpringfoxJsonToGsonSerialer())
//            .setExclusionStrategies(new GsonExclusionStrategy()) //自定义排除转json的字段或者类名
////            .excludeFieldsWithoutExposeAnnotation()//启用	@Expose
//            .create();
}

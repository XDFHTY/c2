package com.cj.core.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "ApiResult 统一返回类")
public class ApiResult<T> {

    @ApiModelProperty(name = "code",value = "状态码")
    private int code;

    @ApiModelProperty(name = "msg",value = "描述")
    private String msg;

    @ApiModelProperty(name = "data",value = "返回数据")
    private T data;

    @ApiModelProperty(name = "params",value = "其他信息，暂未定义")
    private Object params;

    public static Integer SUCCESS_CODE=1001;
    public static Integer FAIL_CODE=1000;


    public static ApiResult SUCCESS() {
        ApiResult apiResult = new ApiResult();
        Result success = Result.SUCCESS;
        apiResult.code = success.getCode();
        apiResult.msg = success.getMsg();
        return apiResult;

    }
    public static ApiResult FAIL() {
        ApiResult apiResult = new ApiResult();
        Result success = Result.FAIL;
        apiResult.code = success.getCode();
        apiResult.msg = success.getMsg();
        return apiResult;

    }
    public static ApiResult CODE_200() {
        ApiResult apiResult = new ApiResult();
        Result success = Result.CODE_200;
        apiResult.code = success.getCode();
        apiResult.msg = success.getMsg();
        return apiResult;

    }
    public static ApiResult CODE_401() {
        ApiResult apiResult = new ApiResult();
        Result success = Result.CODE_401;
        apiResult.code = success.getCode();
        apiResult.msg = success.getMsg();
        return apiResult;

    }

    public static ApiResult CODE_400() {
        ApiResult apiResult = new ApiResult();
        Result success = Result.CODE_400;
        apiResult.code = success.getCode();
        apiResult.msg = success.getMsg();
        return apiResult;

    }
    public static ApiResult CODE_403() {
        ApiResult apiResult = new ApiResult();
        Result success = Result.CODE_403;
        apiResult.code = success.getCode();
        apiResult.msg = success.getMsg();
        return apiResult;

    }
    public static ApiResult CODE_404() {
        ApiResult apiResult = new ApiResult();
        Result success = Result.CODE_404;
        apiResult.code = success.getCode();
        apiResult.msg = success.getMsg();
        return apiResult;

    }
    public static ApiResult CODE_405() {
        ApiResult apiResult = new ApiResult();
        Result success = Result.CODE_405;
        apiResult.code = success.getCode();
        apiResult.msg = success.getMsg();
        return apiResult;

    }
    public static ApiResult CODE_417() {
        ApiResult apiResult = new ApiResult();
        Result success = Result.CODE_417;
        apiResult.code = success.getCode();
        apiResult.msg = success.getMsg();
        return apiResult;

    }
    public static ApiResult CODE_500() {
        ApiResult apiResult = new ApiResult();
        Result success = Result.CODE_500;
        apiResult.code = success.getCode();
        apiResult.msg = success.getMsg();
        return apiResult;

    }


    //业务状态
    public static ApiResult CHECK_AGO() {
        ApiResult apiResult = new ApiResult();
        Result success = Result.CHECK_AGO;
        apiResult.code = success.getCode();
        apiResult.msg = success.getMsg();
        return apiResult;

    }

    public static ApiResult CHECK_AGO(Object data) {
        ApiResult apiResult = new ApiResult();
        Result success = Result.CHECK_AGO;
        apiResult.code = success.getCode();
        apiResult.msg = success.getMsg();
        apiResult.data = data;
        return apiResult;

    }


    public static ApiResult CHECK_IN() {
        ApiResult apiResult = new ApiResult();
        Result success = Result.CHECK_IN;
        apiResult.code = success.getCode();
        apiResult.msg = success.getMsg();
        return apiResult;

    }

    public static ApiResult CHECK_IN(Object data) {
        ApiResult apiResult = new ApiResult();
        Result success = Result.CHECK_IN;
        apiResult.code = success.getCode();
        apiResult.msg = success.getMsg();
        apiResult.data = data;
        return apiResult;

    }


    public static ApiResult CHECK_FAIL() {
        ApiResult apiResult = new ApiResult();
        Result success = Result.CHECK_FAIL;
        apiResult.code = success.getCode();
        apiResult.msg = success.getMsg();
        return apiResult;

    }
    public static ApiResult CHECK_FAIL(Object data) {
        ApiResult apiResult = new ApiResult();
        Result success = Result.CHECK_FAIL;
        apiResult.code = success.getCode();
        apiResult.msg = success.getMsg();
        apiResult.data = data;
        return apiResult;

    }



}

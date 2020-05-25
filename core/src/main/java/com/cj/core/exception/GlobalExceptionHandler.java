package com.cj.core.exception;

import com.cj.core.config.gson.GsonUtil;
import com.cj.core.domain.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

/**
 * @description 全局异常处理: 使用 @RestControllerAdvice + @ExceptionHandler 注解方式实现全局异常处理
 */
@RestControllerAdvice
@RestController
@ApiIgnore
@Slf4j
public class GlobalExceptionHandler implements ErrorController {



    /**
     * 系统异常处理
     *
     * @param e 异常
     * @return
     */
    @ExceptionHandler({Exception.class})    //申明捕获那个异常类
    public ResponseEntity globalExceptionHandler(Exception e, HttpServletRequest request) {
        log.error(e.getMessage(), e);

        ApiResult apiResult = ApiResult.CODE_500();


        if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            apiResult = ApiResult.CODE_404();

        } else if (e instanceof java.net.SocketTimeoutException){
            apiResult = ApiResult.CODE_404();
            apiResult.setMsg("连接超时");

        }
        else if (e instanceof org.springframework.web.HttpRequestMethodNotSupportedException) {
            apiResult = ApiResult.CODE_405();

        } else if (e instanceof com.google.gson.JsonSyntaxException
                || e instanceof com.fasterxml.jackson.databind.JsonMappingException
                || e instanceof org.springframework.http.converter.HttpMessageNotReadableException) {
            log.error("请求参数错误");
            apiResult = ApiResult.CODE_400();
        } else if (e instanceof java.sql.SQLIntegrityConstraintViolationException){
            log.error("SQL完整性约束违反异常");
            apiResult.setMsg("SQL完整性约束违反异常:   "+e.getMessage());
        }else if (e instanceof org.springframework.dao.DuplicateKeyException ) {
            log.error("数据库操作异常");
            apiResult.setMsg("数据库操作异常: " + e.getMessage());
        } else if (e instanceof org.springframework.transaction.NoTransactionException) {
            log.error("事务回滚异常");
            apiResult.setMsg("事务回滚异常: " + e.getMessage());
        }else if (e instanceof org.springframework.dao.QueryTimeoutException){
            log.error("Redis连接超时");
            apiResult.setMsg("Redis连接超时: " + e.getMessage());
        }else if (e instanceof org.springframework.web.HttpMediaTypeNotAcceptableException){
            log.error("response不存在");
            return null;
        }else if (e instanceof org.springframework.web.multipart.MaxUploadSizeExceededException){
            log.error("文件过大");
            apiResult.setMsg("文件过大"+e.getMessage());
        }

        return ResponseEntity.status(apiResult.getCode()).body(apiResult);
    }


    /**
     * 捕获业务异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler({MyException.class})
    public ResponseEntity BusinessExceptionHandler(MyException e) {

        log.error(String.valueOf(e));
        ApiResult apiResult = e.getApiResult();
        System.out.println(GsonUtil.gson.toJson(apiResult));
        if (apiResult.getCode() > 999) {

            return ResponseEntity.status(200).body(apiResult);
        } else {
            return ResponseEntity.status(apiResult.getCode()).body(apiResult);

        }

    }

    /**
     * 捕获404
     */

    private static final String ERROR_PATH = "/error";

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }


    @RequestMapping(value = ERROR_PATH)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ApiResult handleError(HttpServletRequest request) {


        ApiResult apiResult = ApiResult.CODE_404();
        return apiResult;
    }

}

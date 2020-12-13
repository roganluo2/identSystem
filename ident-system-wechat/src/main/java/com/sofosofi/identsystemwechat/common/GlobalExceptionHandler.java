package com.sofosofi.identsystemwechat.common;

import com.sofosofi.identsystemwechat.common.protocol.SofoJSONResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler  {

    /**
     * 定义要捕获的异常 可以多个 @ExceptionHandler({})
     *
     * @param request  request
     * @param e        exception
     * @param response response
     * @return 响应结果
     */
    @ExceptionHandler(CustomException.class)
    public SofoJSONResult customExceptionHandler(HttpServletRequest request, CustomException e, HttpServletResponse response) {
        Integer status = e.getCode();
        String msg = e.getMessage();
        return SofoJSONResult.errorMsg(status, msg);
    }


    @ExceptionHandler(Exception.class)
    public SofoJSONResult runtimeExceptionHandler(HttpServletRequest request,Exception e, HttpServletResponse response) {
        return SofoJSONResult.errorMsg(e.getMessage());
    }


}
package com.sofosofi.identsystemwechat.common;

import com.google.common.base.Throwables;
import com.sofosofi.identsystemwechat.common.protocol.SofoJSONResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 全局异常处理
 */
@RestControllerAdvice
@Slf4j
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
    public String customExceptionHandler(HttpServletRequest request, CustomException e, HttpServletResponse response) throws IOException {
        log.warn(Throwables.getStackTraceAsString(e));
        Integer status = e.getCode();
        String msg = e.getMessage();
        response.setContentType(MediaType.TEXT_PLAIN_VALUE);
        response.setStatus(status);
//        response.getOutputStream().write(msg.getBytes(StandardCharsets.UTF_8));
//        response.getOutputStream().flush();
        return msg;
    }


    @ExceptionHandler(Exception.class)
    public String systemExceptionHandler(HttpServletRequest request,Exception e, HttpServletResponse response) throws IOException {
        log.error(Throwables.getStackTraceAsString(e));
//        return SofoJSONResult.errorMsg(e.getMessage());
        String msg = e.getMessage();
        response.setStatus(500);
        response.setContentType(MediaType.TEXT_PLAIN_VALUE);
//        response.getOutputStream().write("system error".getBytes(StandardCharsets.UTF_8));
//        response.getOutputStream().flush();
        return "system error";
    }


}

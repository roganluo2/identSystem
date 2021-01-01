package com.sofosofi.identsystemwechat.common.aop.aspect.login;

import lombok.Data;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;

@Data
public class LogContext {

    private HttpServletRequest request;

    private Throwable e;

}

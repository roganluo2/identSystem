package com.sofosofi.identsystemwechat.common.aop.aspect;

import com.sofosofi.identsystemwechat.common.aop.aspect.sysoper.ErrorSysLogHandler;
import com.sofosofi.identsystemwechat.common.aop.aspect.sysoper.SuccessSysLogHandler;
import com.sofosofi.identsystemwechat.common.aop.aspect.sysoper.SysLogContext;
import com.sofosofi.identsystemwechat.mapper.SysOperLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
@Slf4j
public class SysOperLogAspect {

    @Resource
    private SysOperLogMapper sysOperLogMapper;

    @Autowired
    private ErrorSysLogHandler errorSysLogHandler;

    @Autowired
    private SuccessSysLogHandler successSysLogHandler;

    @Around("@annotation(com.sofosofi.identsystemwechat.common.aop.annotation.SysLogAop)")
    public Object sysOperLog(ProceedingJoinPoint pjp) throws Throwable {
        try {
            Object result = pjp.proceed();
            SysLogContext context = SysLogContext.builder().pjp(pjp).result(result).build();
            successSysLogHandler.writeSysOperLog(context);
            return result;
        } catch (Throwable e) {
            SysLogContext context = SysLogContext.builder().pjp(pjp).e(e).build();
            errorSysLogHandler.writeSysOperLog(context);
            throw e;
        }
    }



}

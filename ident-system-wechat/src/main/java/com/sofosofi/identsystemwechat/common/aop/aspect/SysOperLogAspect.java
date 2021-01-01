package com.sofosofi.identsystemwechat.common.aop.aspect;

import com.google.common.base.Throwables;
import com.sofosofi.identsystemwechat.common.Constants;
import com.sofosofi.identsystemwechat.common.aop.annotation.SysLogAop;
import com.sofosofi.identsystemwechat.common.aop.aspect.login.ErrorLoginHandler;
import com.sofosofi.identsystemwechat.common.aop.aspect.login.LogContext;
import com.sofosofi.identsystemwechat.common.aop.aspect.login.SuccessLoginHandler;
import com.sofosofi.identsystemwechat.common.aop.aspect.sysoper.ErrorSysLogHandler;
import com.sofosofi.identsystemwechat.common.aop.aspect.sysoper.SuccessSysLogHandler;
import com.sofosofi.identsystemwechat.common.aop.aspect.sysoper.SysLogContext;
import com.sofosofi.identsystemwechat.entity.SysOperLog;
import com.sofosofi.identsystemwechat.mapper.SysLogininforMapper;
import com.sofosofi.identsystemwechat.mapper.SysOperLogMapper;
import com.sofosofi.identsystemwechat.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.plugin2.util.SystemUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

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
        HttpServletRequest request = paramHttpServletRequest(pjp);
        SysLogContext context = SysLogContext.builder().request(request).build();
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        context.setMethod(methodSignature.getMethod());
        try {
            Object result = pjp.proceed();
            context.setResult(result);
            successSysLogHandler.writeSysOperLog(context);
            return result;
        } catch (Throwable e) {
            context.setE(e);
            errorSysLogHandler.writeSysOperLog(context);
            throw e;
        }
    }

    /**
     * 获取参数中列表中的request对象
     * @param pjp
     * @return
     */
    private HttpServletRequest paramHttpServletRequest(ProceedingJoinPoint pjp) {
        Object[] args = pjp.getArgs();
        if (args == null) {
            return null;
        }
        HttpServletRequest request = null;
        for (Object arg : args) {
            if (HttpServletRequest.class.isAssignableFrom(arg.getClass())) {
                request = (HttpServletRequest) arg;
            }
        }
        return request;
    }

}

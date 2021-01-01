package com.sofosofi.identsystemwechat.common.aop.aspect;

import com.google.common.base.Throwables;
import com.sofosofi.identsystemwechat.common.Constants;
import com.sofosofi.identsystemwechat.common.CustomException;
import com.sofosofi.identsystemwechat.common.aop.aspect.login.ErrorLoginHandler;
import com.sofosofi.identsystemwechat.common.aop.aspect.login.LogContext;
import com.sofosofi.identsystemwechat.common.aop.aspect.login.SuccessLoginHandler;
import com.sofosofi.identsystemwechat.entity.SysLogininfor;
import com.sofosofi.identsystemwechat.mapper.SysLogininforMapper;
import com.sofosofi.identsystemwechat.utils.IpAddressUtils;
import com.sofosofi.identsystemwechat.utils.QQWryUtils;
import com.sofosofi.identsystemwechat.utils.SessionUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.bcel.Const;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
@Aspect
@Slf4j
public class LoginLogAspect {

    @Resource
    private SysLogininforMapper logininforMapper;

    @Autowired
    private ErrorLoginHandler errorLoginHandler;

    @Autowired
    private SuccessLoginHandler successLoginHandler;

    @Around("@annotation(com.sofosofi.identsystemwechat.common.aop.annotation.LoginLogAop)")
    public Object loginLog(ProceedingJoinPoint pjp) throws Throwable {
        HttpServletRequest request = paramHttpServletRequest(pjp);
        LogContext logContext = new LogContext();
        logContext.setRequest(request);
        try {
            Object result = pjp.proceed();
            successLoginHandler.wireLog(logContext);
            return result;
        } catch (Throwable e) {
            logContext.setE(e);
            errorLoginHandler.wireLog(logContext);
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

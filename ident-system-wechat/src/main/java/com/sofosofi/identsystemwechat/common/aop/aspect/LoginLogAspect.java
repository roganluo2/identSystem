package com.sofosofi.identsystemwechat.common.aop.aspect;

import com.sofosofi.identsystemwechat.common.aop.aspect.login.ErrorLoginHandler;
import com.sofosofi.identsystemwechat.common.aop.aspect.login.LoginContext;
import com.sofosofi.identsystemwechat.common.aop.aspect.login.SuccessLoginHandler;
import com.sofosofi.identsystemwechat.mapper.SysLogininforMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
        LoginContext loginContext = new LoginContext();
        loginContext.setPjp(pjp);
        try {
            Object result = pjp.proceed();
            successLoginHandler.wireLog(loginContext);
            return result;
        } catch (Throwable e) {
            loginContext.setE(e);
            errorLoginHandler.wireLog(loginContext);
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

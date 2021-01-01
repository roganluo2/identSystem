package com.sofosofi.identsystemwechat.common.aop.aspect.login;

import com.google.common.base.Throwables;
import com.sofosofi.identsystemwechat.common.Constants;
import com.sofosofi.identsystemwechat.entity.SysLogininfor;
import com.sofosofi.identsystemwechat.mapper.SysLogininforMapper;
import com.sofosofi.identsystemwechat.utils.IpAddressUtils;
import com.sofosofi.identsystemwechat.utils.QQWryUtils;
import com.sofosofi.identsystemwechat.utils.SessionUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Slf4j
public abstract class LoginHandler {

    @Resource
    private SysLogininforMapper logininforMapper;

    /**
     * 写登录日志
     * @param logContext
     */
    public void wireLog(LogContext logContext) {
        HttpServletRequest request = logContext.getRequest();
        if (request == null) {
            return;
        }
        SysLogininfor logininfor = new SysLogininfor();
        logininfor.setUserName(SessionUtils.getUserName());
        String ip = IpAddressUtils.getIpAddress(request);
        logininfor.setIpaddr(ip);
        try {
            logininfor.setLoginLocation(QQWryUtils.getLocation(ip));//request
        } catch (Throwable e) {
            log.error("获取location异常：ip:{}, e:{}", ip, Throwables.getStackTraceAsString(e));
        }
        String userAgent = request.getHeader(Constants.HEAD_USER_AGENT);
        logininfor.setBrowser(userAgent);
        logininfor.setOs(request.getHeader(Constants.HEADER_OS_KEY));
        logininfor.setLoginTime(new Date());
        logininfor.setOperatorType(Constants.WECHAT_OPERATION_TYPE);
        logininfor.setStatus(Constants.SYS_STATUS_NORMAL);
        customLogininfo(logininfor, logContext);
        logininforMapper.insertSelective(logininfor);
    }

    protected abstract void customLogininfo(SysLogininfor logininfor, LogContext logContext);


}

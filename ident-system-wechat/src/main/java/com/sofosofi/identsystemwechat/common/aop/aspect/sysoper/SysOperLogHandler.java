package com.sofosofi.identsystemwechat.common.aop.aspect.sysoper;

import com.google.common.base.Throwables;
import com.sofosofi.identsystemwechat.common.Constants;
import com.sofosofi.identsystemwechat.common.aop.annotation.SysLogAop;
import com.sofosofi.identsystemwechat.common.protocol.vo.SysUserVO;
import com.sofosofi.identsystemwechat.entity.SysOperLog;
import com.sofosofi.identsystemwechat.mapper.SysOperLogMapper;
import com.sofosofi.identsystemwechat.service.IUserService;
import com.sofosofi.identsystemwechat.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Slf4j
public abstract class SysOperLogHandler {

    @Resource
    private SysOperLogMapper sysOperLogMapper;

    @Autowired
    private IUserService userService;

    public void writeSysOperLog(SysLogContext context) {
        HttpServletRequest request = HttpRequestUtils.paramHttpServletRequest();
        if (request == null) {
            return;
        }
        MethodSignature methodSignature = (MethodSignature) context.getPjp().getSignature();
        SysLogAop annotation = methodSignature.getMethod().getAnnotation(SysLogAop.class);
        SysOperLog operLog = new SysOperLog();
        operLog.setTitle(annotation.title());
        operLog.setBusinessType(Integer.valueOf(annotation.businessTypeEnum().getType()));
        String method = methodSignature.getMethod().getDeclaringClass().getName() + "." +
                methodSignature.getMethod().getName() + "()";
        operLog.setMethod(method);
        operLog.setRequestMethod(request.getMethod());
        operLog.setOperatorType(Integer.valueOf(Constants.WECHAT_OPERATION_TYPE));
        operLog.setOperName(SessionUtils.getUserName());
        operLog.setOperUrl(request.getRequestURI());
        SysUserVO vo = userService.userInfo(SessionUtils.getUserName());
        operLog.setUserId(Optional.ofNullable(vo).map(SysUserVO::getUserId).orElse(0L));
        String ip = IpAddressUtils.getIpAddress(request);
        operLog.setOperIp(ip);
        try {
            operLog.setOperLocation(QQWryUtils.getLocation(ip));
        } catch (Throwable e) {
            log.error("获取location异常：ip:{}, e:{}", ip, Throwables.getStackTraceAsString(e));
        }
        operLog.setOperParam(getOperParam(context, request));
        operLog.setJsonResult(JsonUtils.objectToJson(context.getResult()));
        operLog.setOperTime(new Date());
        customOptLoginfo(operLog, context);
        sysOperLogMapper.insertSelective(operLog);
    }

    private String getOperParam(SysLogContext context, HttpServletRequest request) {
        String param = JsonUtils.objectToJson(context.getPjp().getArgs());
        if (StringUtils.isEmpty(param)) {
            param = JsonUtils.objectToJson(request.getParameterMap());
        }
        return param;
    }

    protected abstract void customOptLoginfo(SysOperLog operLog, SysLogContext context);

}

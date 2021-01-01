package com.sofosofi.identsystemwechat.common.aop.aspect.sysoper;

import com.google.common.base.Throwables;
import com.sofosofi.identsystemwechat.common.Constants;
import com.sofosofi.identsystemwechat.common.aop.annotation.SysLogAop;
import com.sofosofi.identsystemwechat.common.protocol.vo.SysUserVO;
import com.sofosofi.identsystemwechat.entity.SysOperLog;
import com.sofosofi.identsystemwechat.mapper.SysOperLogMapper;
import com.sofosofi.identsystemwechat.service.IUserService;
import com.sofosofi.identsystemwechat.utils.IpAddressUtils;
import com.sofosofi.identsystemwechat.utils.JsonUtils;
import com.sofosofi.identsystemwechat.utils.QQWryUtils;
import com.sofosofi.identsystemwechat.utils.SessionUtils;
import lombok.extern.slf4j.Slf4j;
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
        HttpServletRequest request = context.getRequest();
        if (request == null) {
            return;
        }
        SysLogAop annotation = context.getMethod().getAnnotation(SysLogAop.class);
        SysOperLog operLog = new SysOperLog();
        operLog.setTitle(annotation.title());
        operLog.setBusinessType(Integer.valueOf(annotation.businessTypeEnum().getType()));
        operLog.setMethod(context.getMethod().getName());
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

        Map<String, String[]> parameterMap = request.getParameterMap();
        operLog.setOperParam(JsonUtils.objectToJson(parameterMap));
        operLog.setJsonResult(JsonUtils.objectToJson(context.getRequest()));
        operLog.setOperTime(new Date());
        customLoginfo(operLog, context);
        sysOperLogMapper.insertSelective(operLog);
    }

    protected abstract void customLoginfo(SysOperLog operLog, SysLogContext context);

}

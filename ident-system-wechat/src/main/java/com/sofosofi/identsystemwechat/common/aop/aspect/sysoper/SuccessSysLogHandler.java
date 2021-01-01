package com.sofosofi.identsystemwechat.common.aop.aspect.sysoper;

import com.sofosofi.identsystemwechat.common.Constants;
import com.sofosofi.identsystemwechat.common.aop.aspect.login.LogContext;
import com.sofosofi.identsystemwechat.common.aop.aspect.login.LoginHandler;
import com.sofosofi.identsystemwechat.entity.SysLogininfor;
import com.sofosofi.identsystemwechat.entity.SysOperLog;
import org.apache.tomcat.util.log.SystemLogHandler;
import org.springframework.stereotype.Service;

@Service
public class SuccessSysLogHandler extends SysOperLogHandler {

    @Override
    protected void customLoginfo(SysOperLog operLog, SysLogContext context) {
        operLog.setStatus(Integer.valueOf(Constants.SYS_STATUS_NORMAL));
    }
}

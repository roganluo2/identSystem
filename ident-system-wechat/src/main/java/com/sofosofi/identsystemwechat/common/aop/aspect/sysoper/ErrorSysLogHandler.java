package com.sofosofi.identsystemwechat.common.aop.aspect.sysoper;

import com.sofosofi.identsystemwechat.common.Constants;
import com.sofosofi.identsystemwechat.common.aop.aspect.login.LogContext;
import com.sofosofi.identsystemwechat.common.aop.aspect.login.LoginHandler;
import com.sofosofi.identsystemwechat.entity.SysLogininfor;
import com.sofosofi.identsystemwechat.entity.SysOperLog;
import com.sofosofi.identsystemwechat.utils.ExceptionUtils;
import org.springframework.stereotype.Service;

@Service
public class ErrorSysLogHandler extends SysOperLogHandler {

    @Override
    protected void customLoginfo(SysOperLog operLog, SysLogContext context) {
        String msg = ExceptionUtils.getMsg(context.getE());
        operLog.setErrorMsg(msg);
        operLog.setStatus(Integer.valueOf(Constants.DEFAULT_RESULT_CODE));
    }
}

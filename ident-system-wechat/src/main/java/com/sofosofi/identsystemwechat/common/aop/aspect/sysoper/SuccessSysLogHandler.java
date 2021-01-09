package com.sofosofi.identsystemwechat.common.aop.aspect.sysoper;

import com.sofosofi.identsystemwechat.common.Constants;
import com.sofosofi.identsystemwechat.entity.SysOperLog;
import com.sofosofi.identsystemwechat.utils.JsonUtils;
import org.springframework.stereotype.Service;

@Service
public class SuccessSysLogHandler extends SysOperLogHandler {

    @Override
    protected void customOptLoginfo(SysOperLog operLog, SysLogContext context) {
        operLog.setStatus(Integer.valueOf(Constants.SYS_STATUS_NORMAL));
    }
}

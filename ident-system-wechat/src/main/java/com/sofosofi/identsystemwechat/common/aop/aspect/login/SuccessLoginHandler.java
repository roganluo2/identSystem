package com.sofosofi.identsystemwechat.common.aop.aspect.login;

import com.sofosofi.identsystemwechat.common.Constants;
import com.sofosofi.identsystemwechat.entity.SysLogininfor;
import org.springframework.stereotype.Service;

@Service
public class SuccessLoginHandler extends LoginHandler {
    @Override
    protected void customLogininfo(SysLogininfor logininfor, LogContext logContext) {
        logininfor.setStatus(Constants.SYS_STATUS_NORMAL);
    }
}

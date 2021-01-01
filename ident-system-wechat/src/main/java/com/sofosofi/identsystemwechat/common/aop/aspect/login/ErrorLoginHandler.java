package com.sofosofi.identsystemwechat.common.aop.aspect.login;

import com.sofosofi.identsystemwechat.common.CustomException;
import com.sofosofi.identsystemwechat.entity.SysLogininfor;
import com.sofosofi.identsystemwechat.utils.ExceptionUtils;
import org.springframework.stereotype.Service;

@Service
public class ErrorLoginHandler extends LoginHandler {


    @Override
    protected void customLogininfo(SysLogininfor logininfor, LogContext logContext) {
        String msg = ExceptionUtils.getMsg(logContext.getE());
        logininfor.setMsg(msg);
    }


}

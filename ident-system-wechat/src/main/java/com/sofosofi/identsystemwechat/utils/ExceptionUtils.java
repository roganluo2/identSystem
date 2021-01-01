package com.sofosofi.identsystemwechat.utils;

import com.sofosofi.identsystemwechat.common.CustomException;

public class ExceptionUtils {

    /**
     * 获取异常信息
     * @param e
     * @return
     */
    public static String getMsg(Throwable e) {
        if (e instanceof CustomException) {
            CustomException customException = (CustomException) e;
            return customException.getMessage();
        }
        return "系统异常";
    }

}

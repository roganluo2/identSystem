package com.sofosofi.identsystemwechat.common;

/**
 * 状态码及提示信息枚举
 */
public enum ReminderEnum {
    NOT_LOGIN_ERROR(505, "您还没有登录"),
    USER_NOT_EXISTS_ERROR(506, "用户不存在或者已经锁定"),
    NOT_TOKEN_ERROR(507, "token失效"),
    ;


    private Integer code;

    private String msg;


    ReminderEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

package com.sofosofi.identsystemwechat.common.protocol;

/**
 * 鉴真列表查询类型枚举
 */
public enum IdentTypeEnum {
    ALL(0), ORIGINAL(1), FAKED(2)
    ;
    private Integer code;

    IdentTypeEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}

package com.sofosofi.identsystemwechat.common;

/**
 * 业务类型（0其它 1新增 2修改 3删除）
 */
public enum  BusinessTypeEnum {
    DEFAULT("0"),
    ADD("1"),
    MODIFY("2"),
    DELETE("3"),
    ;
    private String type;

    BusinessTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

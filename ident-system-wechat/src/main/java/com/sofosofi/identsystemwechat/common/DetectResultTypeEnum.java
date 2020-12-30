package com.sofosofi.identsystemwechat.common;

/**
 * 检测结果枚举
 */
public enum DetectResultTypeEnum {
    FALSE_FLAG("1", "篡改文件"),
    TRUE_FLAG("2", "原始文件"),
    ;

    DetectResultTypeEnum(String retValue, String label) {
        this.retValue = retValue;
        this.label = label;
    }

    /**
     * 鉴真结果值
     */
    private String retValue;

    /**
     * 鉴真标签值
     */
    private String label;

    public String getRetValue() {
        return retValue;
    }

    public void setRetValue(String retValue) {
        this.retValue = retValue;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}

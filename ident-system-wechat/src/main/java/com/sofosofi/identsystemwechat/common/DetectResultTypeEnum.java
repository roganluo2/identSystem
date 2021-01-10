package com.sofosofi.identsystemwechat.common;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 检测结果枚举
 */
public enum DetectResultTypeEnum {
    FALSE_FLAG("0", "篡改文件"),
    TRUE_FLAG("1", "原始文件"),
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

    public static Set<String> getCodeList() {
       return Arrays.stream(DetectResultTypeEnum.values()).map(DetectResultTypeEnum::getRetValue)
               .collect(Collectors.toSet());
    }

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

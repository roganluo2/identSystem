package com.sofosofi.identsystemwechat.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 鉴真程序的执行结果
 */
@Data
public class DetectRes implements Serializable {
    /**
     * 返回结果
     */
    private String RetValue;

    /**
     * 返回值描述
     */
    private String RetDesc;
}

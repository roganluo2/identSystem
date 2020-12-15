package com.sofosofi.identsystemwechat.common.protocol.vo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
public class ProDetectVO {
    /**
     * 鉴真ID,记录id
     */
    @Id
    private Long detectId;

    /**
     * 鉴真结果，0 篡改文件 1 真实文件 2 复印文件
     */
    private Integer resultCode;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private String createTimeStr;

    /**
     * 文件路径
     */
    private String fileUrl;

    /**
     * 文件名
     */
    private String filename;

}
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
    private String resultCode;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private String createTimeStr;

    /**
     * 文件名
     */
    private String filename;

    /**
     * 图片原链接
     */
    private String imageUrl;

    /**
     * 图片缩略图链接
     */
    private String thumbnailImageUrl;

    /**
     * 错误提示
     */
    private String msg;


}
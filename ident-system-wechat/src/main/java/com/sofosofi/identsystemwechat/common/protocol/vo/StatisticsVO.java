package com.sofosofi.identsystemwechat.common.protocol.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统计数据
 */
@Data
@Builder
@AllArgsConstructor
public class StatisticsVO {

    /**
     * 总数
     */
    private Integer total;

    /**
     * 原文件数量
     */
    private Integer original;

    /**
     * 假文件数量
     */
    private Integer faked;

    public StatisticsVO() {
        this.total = 0;
        this.original = 0;
        this.faked = 0;
    }
}

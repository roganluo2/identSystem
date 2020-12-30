package com.sofosofi.identsystemwechat.common.protocol.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProDetectQueryPageDTO {

    @NotNull(message = "页码")
    @Min(1)
    private Integer page;

    @NotNull(message = "条数")
    @Min(1)
    private Integer size;

    /**
     * 鉴真结果类型 0 所有 1 原始 2 篡改
     */
    @NotNull
    private Integer identType;

}

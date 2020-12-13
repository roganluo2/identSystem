package com.sofosofi.identsystemwechat.common.protocol.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProDetectUploadDTO {

    @NotNull(message = "用户ID不能为空")
    @Min(1)
    private Integer userId;

}

package com.sofosofi.identsystemwechat.common.protocol.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdviceDTO {

    @NotEmpty
    @NotNull(message = "标题不能为空")
    private String title;

    @NotEmpty
    @NotNull(message = "标题不能为空")
    private String describes;

}

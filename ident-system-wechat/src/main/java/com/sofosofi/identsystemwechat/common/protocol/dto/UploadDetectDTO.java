package com.sofosofi.identsystemwechat.common.protocol.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadDetectDTO implements Serializable {

    @NotNull
    private MultipartFile file;

    /**
     * 0 - 其他 1 - 本地文件 2 - 高拍仪 3 - 扫描仪 4 - 相机
     */
    @NonNull
    @Min(0)
    private String sourceType;

}

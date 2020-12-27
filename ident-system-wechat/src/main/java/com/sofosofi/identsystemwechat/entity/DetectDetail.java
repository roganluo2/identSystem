package com.sofosofi.identsystemwechat.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetectDetail {

    private String resultCode;

    private String filePath;

    private String resultMsg;

    private String thumbnailPath;
}

package com.sofosofi.identsystemwechat.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 动态配置获取统一入口
 */
@Configuration
@Data
public class DynamicConfig {

    @Value("${wechat.appid:}")
    private String wxAppId;

    @Value("${wechat.appsecret:}")
    private String wxAppSecret;

    @Value("${file.base.path:}")
    private String fileBasePath;

    @Value("${file.base.url:}")
    private String fileBaseUrl;

}

package com.sofosofi.identsystemwechat.common.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @Description 通用的配置
 * @Date 2020/12/17
 * @Created by rogan.luo
 */
@Configuration
public class AppConfiguration {

    @Bean
    public RestTemplate getInstanceByCharset() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return restTemplate;
    }

}

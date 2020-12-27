package com.sofosofi.identsystemwechat.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sofosofi.identsystemwechat.common.protocol.SofoJSONResult;
import com.sofosofi.identsystemwechat.common.protocol.dto.UserBindQueryDTO;
import com.sofosofi.identsystemwechat.common.protocol.dto.UserLoginDTO;
import com.sofosofi.identsystemwechat.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class UserControllerTest extends BaseControllerTest{

    @Resource
    private TestRestTemplate restTemplate;


    @Test
    public void testApiSignature() throws JsonProcessingException {
        String url = "/api/signature";
        UserBindQueryDTO dto = UserBindQueryDTO.builder().code("123456").build();
        SofoJSONResult result = restTemplate.postForObject(url, dto, SofoJSONResult.class);
        log.info("testApiSignature,{}", JsonUtils.objectToJson(result));
        assertThat(result.getStatus()).isEqualTo(SofoJSONResult.success().getStatus());
    }

    @Test
    public void testApiLogin() throws JsonProcessingException {
        String url = "/api/login";
        UserLoginDTO dto = UserLoginDTO.builder().code("053Fss0w3lPaxV2hSY2w3ZIil93Fss0Y").userName("sofosofi")
                .password("123456").build();
        SofoJSONResult result = restTemplate.postForObject(url, dto, SofoJSONResult.class);
        log.info("testApiLogin,{}", JsonUtils.objectToJson(result));
        assertThat(result.getStatus()).isEqualTo(SofoJSONResult.success().getStatus());
    }

    @Test
    public void testApiMe() throws JsonProcessingException {
        String url = "/api/me";
        HttpEntity httpEntity = new HttpEntity<>(headers);
        SofoJSONResult result = restTemplate.exchange(url, HttpMethod.GET , httpEntity, SofoJSONResult.class).getBody();
        log.info("testApiMe,{}", JsonUtils.objectToJson(result));
        assertThat(result.getStatus()).isEqualTo(SofoJSONResult.success().getStatus());
    }





}

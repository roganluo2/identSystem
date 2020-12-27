package com.sofosofi.identsystemwechat.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sofosofi.identsystemwechat.common.Constants;
import com.sofosofi.identsystemwechat.common.protocol.SofoJSONResult;
import com.sofosofi.identsystemwechat.common.protocol.dto.AdviceDTO;
import com.sofosofi.identsystemwechat.common.protocol.dto.UserBindQueryDTO;
import com.sofosofi.identsystemwechat.common.protocol.dto.UserLoginDTO;
import com.sofosofi.identsystemwechat.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class AdviceControllerTest extends BaseControllerTest{

    @Resource
    private TestRestTemplate restTemplate;


    private String tempToken = "43bb82ae-3372-479a-8083-b5b1b7da940b";

    @Before
    public void initHeader() {
        this.headers.set(Constants.HEADER_USER_TOKEN, tempToken);
    }

    @Test
    public void testApiFeedback() throws JsonProcessingException {
        String url = "/api/feedback";
        AdviceDTO adviceDTO = new AdviceDTO();
        adviceDTO.setDescribes("我添加的一条反馈描述");
        adviceDTO.setTitle("我反馈的标题");
        HttpEntity<AdviceDTO> httpEntity = new HttpEntity<>(adviceDTO, headers);
        SofoJSONResult result = restTemplate.exchange(url, HttpMethod.POST , httpEntity, SofoJSONResult.class).getBody();
        log.info("testApiFeedback,{}", JsonUtils.objectToJson(result));
        assertThat(result.getStatus()).isEqualTo(SofoJSONResult.success().getStatus());
    }





}

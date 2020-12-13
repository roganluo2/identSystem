package com.sofosofi.identsystemwechat.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sofosofi.identsystemwechat.common.protocol.SofoJSONResult;
import com.sofosofi.identsystemwechat.common.protocol.dto.ProDetectUploadDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class ProDetectTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testQueryList() throws JsonProcessingException {
        String url = "/queryUserList";
        ProDetectUploadDTO dto = ProDetectUploadDTO.builder().userId(5).build();
        SofoJSONResult result = restTemplate.postForObject(url, dto, SofoJSONResult.class);
        log.info("testQueryList,{}", new ObjectMapper().writeValueAsString(result));
        assertThat(result.getStatus()).isEqualTo(SofoJSONResult.success().getStatus());
    }

}

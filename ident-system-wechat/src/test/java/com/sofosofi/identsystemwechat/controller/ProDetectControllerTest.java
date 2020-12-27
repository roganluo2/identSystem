package com.sofosofi.identsystemwechat.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sofosofi.identsystemwechat.common.protocol.SofoJSONResult;
import com.sofosofi.identsystemwechat.common.protocol.dto.ProDetectDetailDTO;
import com.sofosofi.identsystemwechat.common.protocol.dto.ProDetectQueryPageDTO;
import com.sofosofi.identsystemwechat.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class ProDetectControllerTest extends BaseControllerTest{

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void initHeader() {

    }

    @Test
    public void testQueryList() throws JsonProcessingException {
        String url = "/api/identList";
        ProDetectQueryPageDTO dto = ProDetectQueryPageDTO.builder().page(1).size(2).build();
        HttpEntity<ProDetectQueryPageDTO> httpEntity = new HttpEntity<>(dto, headers);
        SofoJSONResult result = restTemplate.postForObject(url, httpEntity, SofoJSONResult.class);
        log.info("testQueryList,{}", new ObjectMapper().writeValueAsString(result));
        assertThat(result.getStatus()).isEqualTo(SofoJSONResult.success().getStatus());
    }

    @Test
    public void testUpload() {
        this.headers.set("Content-Type", MediaType.MULTIPART_FORM_DATA_VALUE);
        final String filePath = "/Users/tanni/Desktop/rogan/pic";
        final String fileName = "1366c8ee9af1d870eac6aade3e3308c9.jpeg";
        String url = "/api/upload";
        //设置请求体，注意是LinkedMultiValueMap
        FileSystemResource fileSystemResource = new FileSystemResource(filePath+"/"+fileName);
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        form.add("file", fileSystemResource);
        form.add("sourceType",1);
        //用HttpEntity封装整个请求报文
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(form, headers);

        SofoJSONResult result = restTemplate.postForObject(url, httpEntity, SofoJSONResult.class);
        log.info("testUpload,{}", JsonUtils.objectToJson(result));
        assertThat(result.getStatus()).isEqualTo(SofoJSONResult.success().getStatus());
    }



}

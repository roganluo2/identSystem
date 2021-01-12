package com.sofosofi.identsystemwechat.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sofosofi.identsystemwechat.common.protocol.IdentTypeEnum;
import com.sofosofi.identsystemwechat.common.protocol.SofoJSONResult;
import com.sofosofi.identsystemwechat.common.protocol.dto.ProDetectDetailDTO;
import com.sofosofi.identsystemwechat.common.protocol.dto.ProDetectQueryPageDTO;
import com.sofosofi.identsystemwechat.common.protocol.vo.ProDetectVO;
import com.sofosofi.identsystemwechat.common.protocol.vo.StatisticsVO;
import com.sofosofi.identsystemwechat.common.protocol.vo.SysUserVO;
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

import java.util.List;

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
        ProDetectQueryPageDTO dto = ProDetectQueryPageDTO.builder().identType(IdentTypeEnum.ORIGINAL.getCode()).page(1).size(2).build();
        HttpEntity<ProDetectQueryPageDTO> httpEntity = new HttpEntity<>(dto, headers);
        List result = restTemplate.postForObject(url, httpEntity, List.class);
        log.info("testQueryList,{}", new ObjectMapper().writeValueAsString(result));
    }

    @Test
    public void testUpload() {
        this.headers.set("Content-Type", MediaType.MULTIPART_FORM_DATA_VALUE);
        final String filePath = "C:\\Users\\1\\Desktop\\jobFiles\\鉴真\\pic";
        final String fileName = "20201223003112.jpg";
        String url = "http://120.79.226.150:8080/api/upload";
        //设置请求体，注意是LinkedMultiValueMap
        FileSystemResource fileSystemResource = new FileSystemResource(filePath+"/"+fileName);
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        form.add("file", fileSystemResource);
        form.add("sourceType",4);
        form.add("font", "仿宋");
        //用HttpEntity封装整个请求报文
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(form, headers);

        ProDetectVO result = restTemplate.postForObject(url, httpEntity, ProDetectVO.class);
        log.info("testUpload,{}", JsonUtils.objectToJson(result));
    }


    @Test
    public void testStatistics()  {
        String url = "/api/statistics";
        HttpEntity httpEntity = new HttpEntity<>(headers);
        StatisticsVO result = restTemplate.exchange(url, HttpMethod.GET , httpEntity, StatisticsVO.class).getBody();
        log.info("testStatistics,{}", JsonUtils.objectToJson(result));
    }


    @Test
    public void testIdent() throws JsonProcessingException {
        String url = "/api/ident";
        ProDetectDetailDTO dto = ProDetectDetailDTO.builder().detectId(33L).build();
        HttpEntity<ProDetectDetailDTO> httpEntity = new HttpEntity<>(dto, headers);
        ProDetectVO result = restTemplate.postForObject(url, httpEntity, ProDetectVO.class);
        log.info("testIdent,{}", new ObjectMapper().writeValueAsString(result));
    }

}

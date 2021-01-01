package com.sofosofi.identsystemwechat.wechat.service.impl;

import com.sofosofi.identsystemwechat.common.Constants;
import com.sofosofi.identsystemwechat.common.config.Config;
import com.sofosofi.identsystemwechat.utils.JsonUtils;
import com.sofosofi.identsystemwechat.wechat.WechatResult;
import com.sofosofi.identsystemwechat.wechat.enity.WechatResponse;
import com.sofosofi.identsystemwechat.wechat.enity.WechatUser;
import com.sofosofi.identsystemwechat.wechat.service.IWechatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

/**
 * 通过restTemplate调用微信接口
 */
@Service
@Slf4j
public class RestWechatServiceImpl implements IWechatService {

    private String CODE2SESSION_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";

    @Autowired
    private Config config;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public WechatResult<WechatUser> queryUserByCode(String code) {
        String url = String.format(CODE2SESSION_URL, config.getWxAppId(), config.getWxAppSecret(), code);
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<String>(null, requestHeaders);
        WechatResponse response = executeRequest(url, HttpMethod.GET , requestEntity);
        WechatUser wechatUser = new WechatUser();
        wechatUser.setOpenid(response.getOpenid());
        wechatUser.setSessionKey(response.getSession_key());
        WechatResult<WechatUser> result = new WechatResult<>();
        result.setData(wechatUser);
        result.setErrmsg(response.getErrmsg());
        //微信接口，调用正常没有返回值，这里补充返回值
        result.setErrorcode(Optional.ofNullable(response.getErrcode()).orElse(Constants.SUCCESS));
        return result;
    }


    @Override
    public WechatResult<WechatUser> queryUserByCodeMock(String code) {
        WechatUser wechatUser = new WechatUser();
        wechatUser.setOpenid("oGCt35MtDhfuSivILdG9GYlIT80k");
        wechatUser.setSessionKey("oGCt35MtDhfuSivILdG9GYlIT80k");
        WechatResult<WechatUser> result = new WechatResult<>();
        result.setData(wechatUser);
        result.setErrmsg("错误提示");
        //微信接口，调用正常没有返回值，这里补充返回值
        result.setErrorcode(Constants.SUCCESS);
        return result;
    }

    /**
     * 执行接口调用，输出日志，并处理null
     * @param url
     * @param method
     * @param requestEntity
     * @return
     */
    private WechatResponse executeRequest(String url, HttpMethod method, HttpEntity<String> requestEntity) {
        log.info("http request send start, url : {}, request : {}", url, JsonUtils.objectToJson(requestEntity));
        String str = restTemplate.exchange(url, method, requestEntity, String.class).getBody();
        log.info("http request send end, url : {}, request : {}, res : {}", url,
                JsonUtils.objectToJson(requestEntity), str);
        WechatResponse response = JsonUtils.jsonToPojo(str, WechatResponse.class);
        if (response == null) {
            log.error("http request error, url : {}, request : {}", url,
                    JsonUtils.objectToJson(requestEntity));
            WechatResponse errorResponse = new WechatResponse();
            errorResponse.setErrcode(10000);
            errorResponse.setErrmsg("接口调用异常!");
            return errorResponse;
        }
        return response;
    }
}

package com.sofosofi.identsystemwechat.wechat.service.impl;

import com.sofosofi.identsystemwechat.wechat.WechatResult;
import com.sofosofi.identsystemwechat.wechat.enity.WechatUser;
import com.sofosofi.identsystemwechat.wechat.service.IWechatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * 通过restTemplate调用微信接口
 */
@Service
public class RestWechatServiceImpl implements IWechatService {

    private String CODE2SESSION_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";

    @Value("${wechat.appid:}")
    private String wxAppId;

    @Value("${wechat.appsecret:}")
    private String wxAppSecret;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public WechatResult<WechatUser> queryUserByCode(String code) {
        String url = String.format(CODE2SESSION_URL, wxAppId, wxAppSecret, code);
        HashMap map = restTemplate.getForObject(url, HashMap.class);
        WechatUser wechatUser = new WechatUser();
        wechatUser.setOpenid((String) map.get("openid"));
        wechatUser.setSessionKey((String) map.get("session_key"));
        WechatResult<WechatUser> result = new WechatResult<>();
        result.setData(wechatUser);
        result.setErrmsg((String) map.get("errmsg"));
        result.setErrorcode((Integer) map.get("errcode"));
        return result;
    }
}

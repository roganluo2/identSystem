package com.sofosofi.identsystemwechat.wechat.enity;

import lombok.Data;

/**
 * 微信用户信息
 */
@Data
public class WechatUser {

    private String openid;

    private String sessionKey;

    private String unionid;

}

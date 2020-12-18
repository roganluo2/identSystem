package com.sofosofi.identsystemwechat.wechat.enity;

import lombok.Data;

/**
 * @Description 微信接口调用响应结果
 * @Date 2020/12/18
 * @Created by rogan.luo
 */
@Data
public class WechatResponse {

    private Integer errcode;

    private String errmsg;

    private String openid;

    private String session_key;

}

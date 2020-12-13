package com.sofosofi.identsystemwechat.wechat.service;

import com.sofosofi.identsystemwechat.wechat.WechatResult;
import com.sofosofi.identsystemwechat.wechat.enity.WechatUser;

/**
 * 微信接口
 */
public interface IWechatService {

    /**
     * 根据小程序登陆码，查询用户信息
     * @param code
     * @return
     */
    WechatResult<WechatUser> queryUserByCode(String code);

}

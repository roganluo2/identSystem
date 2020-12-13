package com.sofosofi.identsystemwechat.wechat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 微信接口调用返回
 * @param <T>
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WechatResult<T> {

    private Integer errorcode;

    private String errmsg;

    private T data;

}

package com.sofosofi.identsystemwechat.common.protocol.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

/**
 * 用户显示层信息
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUserVO {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 微信opneid
     */
    private String openid;

    /**
     * 用户账号
     */
    private String userName;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String phonenumber;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 公司地址
     */
    private String companyAddr;

    /**
     * 用户性别（0男 1女 2未知）
     */
    private String sex;

    /**
     * 头像地址
     */
    private String avatar;
    /**
     * 备注
     */
    private String remark;

    /**
     * 登录token,用于后续的校验
     */
    private String token;

}

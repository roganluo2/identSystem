package com.sofosofi.identsystemwechat.service;

import com.sofosofi.identsystemwechat.common.protocol.dto.UserLoginDTO;
import com.sofosofi.identsystemwechat.common.protocol.vo.SysUserVO;

/**
 * 用户接口Api
 */
public interface IUserService {

    /**
     * 根据小程序状态码查询绑定的用户信息
     * @param code
     * @return
     */
    SysUserVO queryBindUserInfo(String code);

    /**
     * 用户登陆绑定
     * @param dto
     * @return
     */
    SysUserVO userLogin(UserLoginDTO dto);
}

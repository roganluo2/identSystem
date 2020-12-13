package com.sofosofi.identsystemwechat.service;

import com.sofosofi.identsystemwechat.entity.ProDetect;

import java.util.List;

public interface IProDetectService {

    /**
     * 查询用户
     * @param userId
     * @return
     */
    List<ProDetect> queryUserList(Integer userId);
}

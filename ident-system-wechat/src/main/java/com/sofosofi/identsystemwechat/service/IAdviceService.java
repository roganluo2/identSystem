package com.sofosofi.identsystemwechat.service;

import com.sofosofi.identsystemwechat.common.protocol.dto.AdviceDTO;

/**
 * @Description 反馈操作处理类
 * @Date 2020/12/15
 * @Created by rogan.luo
 */
public interface IAdviceService {

    /**
     * 保存反馈建议
     * @param dto
     */
    void saveAdvice(AdviceDTO dto);
}

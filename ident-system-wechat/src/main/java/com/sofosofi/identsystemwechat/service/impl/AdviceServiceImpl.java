package com.sofosofi.identsystemwechat.service.impl;

import com.sofosofi.identsystemwechat.common.protocol.dto.AdviceDTO;
import com.sofosofi.identsystemwechat.entity.SysAdvice;
import com.sofosofi.identsystemwechat.mapper.SysAdviceMapper;
import com.sofosofi.identsystemwechat.service.IAdviceService;
import com.sofosofi.identsystemwechat.utils.SessionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Description 反馈管理
 * @Date 2020/12/16
 * @Created by rogan.luo
 */
@Service
public class AdviceServiceImpl implements IAdviceService {

    @Autowired
    private SysAdviceMapper adviceMapper;

    @Override
    public void saveAdvice(AdviceDTO dto) {
        String userName = SessionUtils.getUserName();
        SysAdvice advice  = new SysAdvice();
        BeanUtils.copyProperties(dto, advice);
        advice.setCreateBy(userName);
        Date now = new Date();
        advice.setCreateTime(now);
        //TODO 默认加载字典值
        advice.setIsRead("N");
        advice.setOperatorType("4");
        advice.setUpdateBy(userName);
        advice.setUpdateTime(now);
        adviceMapper.insertSelective(advice);
    }
}

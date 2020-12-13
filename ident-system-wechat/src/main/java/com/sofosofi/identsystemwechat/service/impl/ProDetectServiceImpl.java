package com.sofosofi.identsystemwechat.service.impl;

import com.sofosofi.identsystemwechat.entity.ProDetect;
import com.sofosofi.identsystemwechat.mapper.ProDetectMapper;
import com.sofosofi.identsystemwechat.service.IProDetectService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProDetectServiceImpl implements IProDetectService {

    @Resource
    private ProDetectMapper proDetectMapper;

    @Override
    public List<ProDetect> queryUserList(Integer userId) {
        Example example = new Example.Builder(ProDetect.class).build();
        example.createCriteria().andEqualTo("createBy", userId);
        return proDetectMapper.selectByExample(example);
    }
}

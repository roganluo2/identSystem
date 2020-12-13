package com.sofosofi.identsystemwechat.service.impl;

import com.sofosofi.identsystemwechat.common.Constants;
import com.sofosofi.identsystemwechat.common.CustomException;
import com.sofosofi.identsystemwechat.common.ReminderEnum;
import com.sofosofi.identsystemwechat.common.protocol.vo.SysUserVO;
import com.sofosofi.identsystemwechat.entity.SysUser;
import com.sofosofi.identsystemwechat.entity.SysUserAccount;
import com.sofosofi.identsystemwechat.mapper.SysUserAccountMapper;
import com.sofosofi.identsystemwechat.mapper.SysUserMapper;
import com.sofosofi.identsystemwechat.service.IUserService;
import com.sofosofi.identsystemwechat.wechat.WechatResult;
import com.sofosofi.identsystemwechat.wechat.enity.WechatUser;
import com.sofosofi.identsystemwechat.wechat.service.IWechatService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * 用户逻辑实现类
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SysUserAccountMapper accountMapper;

    @Resource
    private IWechatService wechatService;

    /**
     * 1.通过code 调用微信接口，换取openid
     * 2.根据openid查询db中是否有绑定的用户
     * 3.返回绑定的用户或者跑出异常
     * @param code
     * @return
     */
    @Override
    public SysUserVO queryBindUserInfo(String code) {
        WechatResult<WechatUser> result = wechatService.queryUserByCode(code);
        if (!result.getErrorcode().equals(Constants.SUCCESS)) {
            throw new CustomException(result.getErrmsg());
        }
        SysUserAccount account = queryAccountByOpenId(result.getData().getOpenid());
        if (account == null) {
            throw new CustomException(ReminderEnum.NOT_BIND_ERROR.getCode(), ReminderEnum.NOT_BIND_ERROR.getMsg());
        }
        SysUser user = getUserByUserName(account.getUserName());
        if (user == null) {
            throw new CustomException(ReminderEnum.USER_NOT_EXISTS_ERROR.getCode(),
                    ReminderEnum.USER_NOT_EXISTS_ERROR.getMsg());
        }
        SysUserVO vo = new SysUserVO();
        BeanUtils.copyProperties(user, vo);
        return vo;
    }

    /**
     * 根据用户名查询用户信息
     * @param userName
     * @return
     */
    private SysUser getUserByUserName(String userName) {
        SysUser sysUser = new SysUser();
        sysUser.setUserName(userName);
        String normal = "0";
        sysUser.setStatus(normal);
        return sysUserMapper.selectOne(sysUser);
    }


    /**
     * 根据opneid 查询用户信息
     * @param openid
     * @return
     */
    private SysUserAccount queryAccountByOpenId(String openid) {
        SysUserAccount query = new SysUserAccount();
        query.setAccountId(openid);
        List<SysUserAccount> result = accountMapper.select(query);
        return Optional.ofNullable(result).filter(e -> e.size() > 0).map(e -> e.get(e.size() - 1)).orElse(null);
    }
}
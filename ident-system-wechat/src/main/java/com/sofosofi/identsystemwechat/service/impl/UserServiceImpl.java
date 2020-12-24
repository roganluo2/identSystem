package com.sofosofi.identsystemwechat.service.impl;

import com.sofosofi.identsystemwechat.common.Constants;
import com.sofosofi.identsystemwechat.common.CustomException;
import com.sofosofi.identsystemwechat.common.RedisOperator;
import com.sofosofi.identsystemwechat.common.ReminderEnum;
import com.sofosofi.identsystemwechat.common.protocol.dto.UserLoginDTO;
import com.sofosofi.identsystemwechat.common.protocol.vo.SysUserVO;
import com.sofosofi.identsystemwechat.entity.SysUser;
import com.sofosofi.identsystemwechat.entity.SysUserAccount;
import com.sofosofi.identsystemwechat.mapper.SysUserAccountMapper;
import com.sofosofi.identsystemwechat.mapper.SysUserMapper;
import com.sofosofi.identsystemwechat.service.IUserService;
import com.sofosofi.identsystemwechat.wechat.WechatResult;
import com.sofosofi.identsystemwechat.wechat.enity.WechatUser;
import com.sofosofi.identsystemwechat.wechat.service.IWechatService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @Autowired
    private RedisOperator redis;

    /**
     * 1.通过code 调用微信接口，换取openid
     * 2.根据openid查询db中是否有绑定的用户
     * 3.返回绑定的用户或者抛出异常，引导登录
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
            throw new CustomException(ReminderEnum.NOT_LOGIN_ERROR.getCode(), ReminderEnum.NOT_LOGIN_ERROR.getMsg());
        }
        SysUser user = getUserByUserName(account.getUserName());
        if (user == null) {
            throw new CustomException(ReminderEnum.USER_NOT_EXISTS_ERROR.getCode(),
                    ReminderEnum.USER_NOT_EXISTS_ERROR.getMsg());
        }
        String token = setUserSessionToken(user.getUserName());
        SysUserVO vo = new SysUserVO();
        BeanUtils.copyProperties(user, vo);
        vo.setToken(token);
        return vo;
    }

    private String setUserSessionToken(String userName) {
        String token = UUID.randomUUID().toString();
        redis.set(String.format(Constants.USER_REDIS_SESSION, userName), token, 24 * 3600 * 31);
        return token;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SysUserVO userLogin(UserLoginDTO dto) {
        SysUser sysUser = getByUserNameAndPassword(dto.getUserName(), dto.getPassword());
        if (sysUser == null) {
            throw new CustomException("用户名或者密码错误！");
        }
        WechatResult<WechatUser> result = wechatService.queryUserByCode(dto.getCode());
        if (!result.getErrorcode().equals(Constants.SUCCESS)) {
            throw new CustomException("code 状态异常");
        }
        bindOpenid(sysUser, result.getData().getOpenid());
        SysUserVO vo = new SysUserVO();
        BeanUtils.copyProperties(sysUser, vo);
        String token = setUserSessionToken(vo.getUserName());
        vo.setToken(token);
        return vo;
    }

    @Override
    public SysUserVO userInfo(String userName) {
        SysUser sysUser = getUserByUserName(userName);
        SysUserVO vo = new SysUserVO();
        BeanUtils.copyProperties(sysUser, vo);
        return vo;
    }

    /**
     * 建立绑定关系
     * @param sysUser
     * @param openid
     */
    private void bindOpenid(SysUser sysUser, String openid) {
        SysUserAccount account = queryAccountByOpenId(openid);
        if (account == null) {
            insertBind(sysUser, openid);
        } else if (!account.getUserName().equals(sysUser.getUserName())) {
            SysUserAccount update = new SysUserAccount();
            update.setUserAccountId(account.getUserAccountId());
            update.setAccountId(StringUtils.EMPTY);
            update.setUpdateBy(sysUser.getUserName());
            update.setUpdateTime(new Date());
            accountMapper.updateByPrimaryKeySelective(update);
            insertBind(sysUser, openid);
        }
    }

    private void insertBind(SysUser sysUser, String openid) {
        SysUserAccount account = queryAccountByUserNameAndOpenid(sysUser.getUserName(), StringUtils.EMPTY);
        if (account == null) {
            SysUserAccount insert = new SysUserAccount();
            insert.setAccountId(openid);
            insert.setCreateBy(sysUser.getUserName());
            insert.setCreateTime(new Date());
            insert.setState(Constants.SYS_STATUS_NORMAL);
            insert.setUpdateBy(sysUser.getUserName());
            insert.setUpdateTime(new Date());
            insert.setUserName(sysUser.getUserName());
            accountMapper.insertSelective(insert);
        } else  {
            SysUserAccount update = new SysUserAccount();
            update.setUserAccountId(account.getUserAccountId());
            update.setAccountId(openid);
            update.setUpdateBy(sysUser.getUserName());
            update.setUpdateTime(new Date());
            accountMapper.updateByPrimaryKeySelective(update);
        }
    }

    private SysUserAccount queryAccountByUserNameAndOpenid(String userName, String accountid) {
        SysUserAccount query = new SysUserAccount();
        query.setUserName(userName);
        query.setAccountId(accountid);
        List<SysUserAccount> list = accountMapper.select(query);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }

    /**
     * 根据username password 获取用户信息
     * @param userName
     * @param password
     * @return
     */
    private SysUser getByUserNameAndPassword(String userName, String password) {
        SysUser query = new SysUser();
        query.setStatus(Constants.SYS_STATUS_NORMAL);
        query.setUserName(userName);
//        query.setPassword(password);
        return sysUserMapper.selectOne(query);
    }

    /**
     * 根据用户名查询用户信息
     * @param userName
     * @return
     */
    private SysUser getUserByUserName(String userName) {
        SysUser sysUser = new SysUser();
        sysUser.setUserName(userName);
        sysUser.setStatus(Constants.STATUS_NORMAL);
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

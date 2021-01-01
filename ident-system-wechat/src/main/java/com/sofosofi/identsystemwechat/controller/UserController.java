package com.sofosofi.identsystemwechat.controller;

import com.sofosofi.identsystemwechat.common.BusinessTypeEnum;
import com.sofosofi.identsystemwechat.common.aop.annotation.LoginLogAop;
import com.sofosofi.identsystemwechat.common.aop.annotation.SysLogAop;
import com.sofosofi.identsystemwechat.common.protocol.dto.UserBindQueryDTO;
import com.sofosofi.identsystemwechat.common.protocol.dto.UserLoginDTO;
import com.sofosofi.identsystemwechat.common.protocol.vo.SysUserVO;
import com.sofosofi.identsystemwechat.service.IUserService;
import com.sofosofi.identsystemwechat.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 根据小程序code获取用户userName token
     * @param dto 查询条件
     * @return
     */
    @PostMapping(value = "/signature", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @SysLogAop(title = "快捷登录", businessTypeEnum = BusinessTypeEnum.ADD)
    public SysUserVO queryBindUserInfo(@Valid @RequestBody UserBindQueryDTO dto) {
        SysUserVO userVO = userService.queryBindUserInfo(dto.getCode());
        return userVO;
    }

    /**
     * 用户账号密码绑定,登陆绑定
     * @param dto 查询条件
     * @return
     */
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @LoginLogAop
    public SysUserVO userLogin(HttpServletRequest request, @Valid @RequestBody UserLoginDTO dto) {
        SysUserVO userVO = userService.userLogin(dto);
        return userVO;
    }

    /**
     * 获取个人用户信息
     * @return
     */
    @GetMapping(value = "/me", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public SysUserVO userInfo() {
        String userName = SessionUtils.getUserName();
        SysUserVO userVO = userService.userInfo(userName);
        return userVO;
    }

    /**
     * 登出
     * @return
     */
    @GetMapping(value = "/logout")
    public void logout(String openid) {
        String userName = SessionUtils.getUserName();
        userService.logout(userName, openid);
    }

}

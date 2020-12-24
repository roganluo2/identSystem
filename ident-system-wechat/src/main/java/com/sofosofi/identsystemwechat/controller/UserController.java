package com.sofosofi.identsystemwechat.controller;

import com.sofosofi.identsystemwechat.common.protocol.SofoJSONResult;
import com.sofosofi.identsystemwechat.common.protocol.dto.UserBindQueryDTO;
import com.sofosofi.identsystemwechat.common.protocol.dto.UserLoginDTO;
import com.sofosofi.identsystemwechat.common.protocol.vo.SysUserVO;
import com.sofosofi.identsystemwechat.service.IUserService;
import com.sofosofi.identsystemwechat.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping(value = "/signature", produces = "application/json", consumes = "application/json")
    public SofoJSONResult<SysUserVO> queryBindUserInfo(@Valid @RequestBody UserBindQueryDTO dto) {
        SysUserVO userVO = userService.queryBindUserInfo(dto.getCode());
        return SofoJSONResult.ok(userVO);
    }

    /**
     * 用户账号密码绑定,登陆绑定
     * @param dto 查询条件
     * @return
     */
    @PostMapping("/login")
    public SofoJSONResult<SysUserVO> userLogin(@Valid @RequestBody UserLoginDTO dto) {
        SysUserVO userVO = userService.userLogin(dto);
        return SofoJSONResult.ok(userVO);
    }

    /**
     * 获取个人用户信息
     * @return
     */
    @GetMapping("/me")
    public SofoJSONResult<SysUserVO> userInfo() {
        String userName = SessionUtils.getUserName();
        SysUserVO userVO = userService.userInfo(userName);
        return SofoJSONResult.ok(userVO);
    }


}

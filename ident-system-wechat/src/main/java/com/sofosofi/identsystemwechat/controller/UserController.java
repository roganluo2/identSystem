package com.sofosofi.identsystemwechat.controller;

import com.sofosofi.identsystemwechat.common.protocol.SofoJSONResult;
import com.sofosofi.identsystemwechat.common.protocol.dto.UserBindQueryDTO;
import com.sofosofi.identsystemwechat.common.protocol.vo.SysUserVO;
import com.sofosofi.identsystemwechat.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 根据小程序code获取用户信息
     * @param dto 查询条件
     * @return
     */
    @PostMapping("/queryBindUserInfo")
    public SofoJSONResult<SysUserVO> queryBindUserInfo(@Valid @RequestBody UserBindQueryDTO dto) {
        SysUserVO userVO = userService.queryBindUserInfo(dto.getCode());
        return SofoJSONResult.ok(userVO);
    }

}

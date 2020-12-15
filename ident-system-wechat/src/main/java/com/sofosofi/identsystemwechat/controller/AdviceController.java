package com.sofosofi.identsystemwechat.controller;

import com.sofosofi.identsystemwechat.common.protocol.SofoJSONResult;
import com.sofosofi.identsystemwechat.common.protocol.dto.UserBindQueryDTO;
import com.sofosofi.identsystemwechat.common.protocol.dto.UserLoginDTO;
import com.sofosofi.identsystemwechat.common.protocol.dto.UserQueryDTO;
import com.sofosofi.identsystemwechat.common.protocol.vo.SysUserVO;
import com.sofosofi.identsystemwechat.service.IAdviceService;
import com.sofosofi.identsystemwechat.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/advice")
public class AdviceController {

    @Autowired
    private IAdviceService adviceService;

    /**
     * 提交反馈
     * @return
     */
    @PostMapping("/saveAdvice")
    public SofoJSONResult saveAdvice(@Valid @RequestBody AdviceDTO dto) {
        adviceService.saveAdvice(dto);
        return SofoJSONResult.ok(null);
    }


}

package com.sofosofi.identsystemwechat.controller;

import com.sofosofi.identsystemwechat.common.protocol.dto.AdviceDTO;
import com.sofosofi.identsystemwechat.service.IAdviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class AdviceController {

    @Autowired
    private IAdviceService adviceService;

    /**
     * 提交反馈
     * @return
     */
    @PostMapping("/feedback")
    public void saveAdvice(@Valid @RequestBody AdviceDTO dto) {
        adviceService.saveAdvice(dto);
    }


}

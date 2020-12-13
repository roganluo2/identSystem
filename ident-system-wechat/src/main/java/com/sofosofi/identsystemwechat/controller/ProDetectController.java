package com.sofosofi.identsystemwechat.controller;

import com.sofosofi.identsystemwechat.common.protocol.SofoJSONResult;
import com.sofosofi.identsystemwechat.common.protocol.dto.ProDetectUploadDTO;
import com.sofosofi.identsystemwechat.entity.ProDetect;
import com.sofosofi.identsystemwechat.mapper.ProDetectMapper;
import com.sofosofi.identsystemwechat.service.IProDetectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
public class ProDetectController {

    @Autowired
    private IProDetectService proDetectService;

    @RequestMapping("/queryUserList")
    public SofoJSONResult<List<ProDetect>> queryUserList(@RequestBody @Valid ProDetectUploadDTO dto) {
        List<ProDetect> proDetects = proDetectService.queryUserList(dto.getUserId());
        return SofoJSONResult.ok(proDetects);
    }

    @PostMapping(value="/uploadDetect", headers="content-type=multipart/form-data")
    public SofoJSONResult uploadDetect(String userId, MultipartFile file) throws Exception {
     return null;
    }


}

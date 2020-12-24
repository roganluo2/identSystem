package com.sofosofi.identsystemwechat.controller;

import com.sofosofi.identsystemwechat.common.protocol.SofoJSONResult;
import com.sofosofi.identsystemwechat.common.protocol.dto.*;
import com.sofosofi.identsystemwechat.common.protocol.vo.ProDetectVO;
import com.sofosofi.identsystemwechat.common.protocol.vo.SysUserVO;
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
@RequestMapping("/api")
public class ProDetectController {

    @Autowired
    private IProDetectService proDetectService;


    @PostMapping(value="/upload", headers="content-type=multipart/form-data")
    public SofoJSONResult<ProDetectVO> uploadDetect(@Valid @RequestBody UploadDetectDTO dto) throws Exception {
        ProDetectVO vo = proDetectService.uploadDetect(dto);
        return SofoJSONResult.ok(vo);
    }

    /**
     * 获取鉴真详情信息
     * @param dto 查询条件
     * @return
     */
    @PostMapping("/ident")
    public SofoJSONResult<ProDetectVO> queryProDetectDetail(@Valid @RequestBody ProDetectDetailDTO dto) {
        ProDetectVO vo = proDetectService.queryProDetectDetail(dto);
        return SofoJSONResult.ok(vo);
    }

    /**
     * 获取鉴真列表，支持分页
     * @param dto 查询条件
     * @return
     */
    @PostMapping("/identList")
    public SofoJSONResult<List<ProDetectVO>> queryProDetectPage(@Valid @RequestBody ProDetectQueryPageDTO dto) {
        List<ProDetectVO> proDetectVOS = proDetectService.queryProDetectPage(dto);
        return SofoJSONResult.ok(proDetectVOS);
    }

}

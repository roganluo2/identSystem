package com.sofosofi.identsystemwechat.controller;

import com.sofosofi.identsystemwechat.common.protocol.dto.*;
import com.sofosofi.identsystemwechat.common.protocol.vo.ProDetectVO;
import com.sofosofi.identsystemwechat.common.protocol.vo.StatisticsVO;
import com.sofosofi.identsystemwechat.service.IProDetectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProDetectController {

    @Autowired
    private IProDetectService proDetectService;


    @PostMapping(value="/upload", headers="content-type=multipart/form-data")
    public ProDetectVO uploadDetect(@Valid UploadDetectDTO dto) throws Exception {
        ProDetectVO vo = proDetectService.uploadDetect(dto);
        return vo;
    }

    /**
     * 获取鉴真详情信息
     * @param dto 查询条件
     * @return
     */
    @PostMapping("/ident")
    public ProDetectVO queryProDetectDetail(@Valid @RequestBody ProDetectDetailDTO dto) {
        ProDetectVO vo = proDetectService.queryProDetectDetail(dto);
        return vo;
    }

    /**
     * 获取鉴真列表，支持分页
     * @param dto 查询条件
     * @return
     */
    @PostMapping("/identList")
    public List<ProDetectVO> queryProDetectPage(@Valid @RequestBody ProDetectQueryPageDTO dto) {
        List<ProDetectVO> proDetectVOS = proDetectService.queryProDetectPage(dto);
        return proDetectVOS;
    }

    /**
     * 获取鉴真统计数据，按照个人获取
     * @return
     */
    @GetMapping(value = "/statistics", consumes = MediaType.APPLICATION_JSON_VALUE)
    public StatisticsVO statistics() {
        StatisticsVO statisticsVO = proDetectService.statistics();
        return statisticsVO;
    }


}

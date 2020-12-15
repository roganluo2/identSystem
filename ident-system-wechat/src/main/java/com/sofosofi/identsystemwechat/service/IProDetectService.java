package com.sofosofi.identsystemwechat.service;

import com.sofosofi.identsystemwechat.common.protocol.dto.ProDetectDetailDTO;
import com.sofosofi.identsystemwechat.common.protocol.dto.ProDetectQueryPageDTO;
import com.sofosofi.identsystemwechat.common.protocol.dto.UploadDetectDTO;
import com.sofosofi.identsystemwechat.common.protocol.vo.ProDetectVO;
import com.sofosofi.identsystemwechat.entity.ProDetect;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IProDetectService {

    /**
     * 查询用户
     * @param userId
     * @return
     */
    List<ProDetect> queryUserList(Integer userId);

    /**
     * 查询鉴真记录列表，支持分页
     * @param dto
     * @return
     */
    List<ProDetectVO> queryProDetectPage(ProDetectQueryPageDTO dto);

    /**
     * 获取鉴真详情页
     * @param dto
     * @return
     */
    ProDetectVO queryProDetectDetail(ProDetectDetailDTO dto);

    /**
     * 上传文件，处理鉴真
     * @return
     */
    ProDetectVO uploadDetect(UploadDetectDTO dto) throws IOException;
}

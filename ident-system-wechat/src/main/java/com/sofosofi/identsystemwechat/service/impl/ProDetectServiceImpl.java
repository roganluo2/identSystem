package com.sofosofi.identsystemwechat.service.impl;

import com.github.pagehelper.PageHelper;
import com.sofosofi.identsystemwechat.common.Constants;
import com.sofosofi.identsystemwechat.common.CustomException;
import com.sofosofi.identsystemwechat.common.config.DynamicConfig;
import com.sofosofi.identsystemwechat.common.protocol.dto.ProDetectDetailDTO;
import com.sofosofi.identsystemwechat.common.protocol.dto.ProDetectQueryPageDTO;
import com.sofosofi.identsystemwechat.common.protocol.dto.UploadDetectDTO;
import com.sofosofi.identsystemwechat.common.protocol.vo.ProDetectVO;
import com.sofosofi.identsystemwechat.entity.DetectDetail;
import com.sofosofi.identsystemwechat.entity.DetectInfo;
import com.sofosofi.identsystemwechat.entity.ProDetect;
import com.sofosofi.identsystemwechat.mapper.ProDetectMapper;
import com.sofosofi.identsystemwechat.service.IProDetectService;
import com.sofosofi.identsystemwechat.utils.JsonUtils;
import com.sofosofi.identsystemwechat.utils.SessionUtils;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Slf4j
public class ProDetectServiceImpl implements IProDetectService {

    @Autowired
    private DynamicConfig dynamicConfig;

    @Resource
    private ProDetectMapper proDetectMapper;

    @Override
    public List<ProDetectVO> queryProDetectPage(ProDetectQueryPageDTO dto) {
        ProDetect query = new ProDetect();
        query.setCreateBy(SessionUtils.getUserName());
        query.setOperatorType(Constants.WECHAT_OPERATION_TYPE);
        PageHelper.startPage(dto.getPage(), dto.getSize());
        List<ProDetect> proDetects =
                proDetectMapper.select(query);
        return getMappingVOs(proDetects);
    }

    @Override
    public ProDetectVO queryProDetectDetail(ProDetectDetailDTO dto) {
        ProDetect query = new ProDetect();
        query.setDetectId(dto.getProDetectId());
        query.setCreateBy(SessionUtils.getUserName());
        //只是查询小程序的，其他的查询了也展示不了
        query.setOperatorType(Constants.WECHAT_OPERATION_TYPE);
        ProDetect detect = proDetectMapper.selectOne(query);
        if (detect == null) {
            throw new CustomException("鉴真记录不存在!");
        }
        DetectInfo detectInfo = JsonUtils.jsonToPojo(detect.getDetectInfo(), DetectInfo.class);
        List<DetectDetail> detectDetails = Optional.ofNullable(detectInfo).map(DetectInfo::getAll)
                .orElse(Collections.emptyList());
        ProDetectVO vo = new ProDetectVO();
        if (CollectionUtils.isNotEmpty(detectDetails)) {
            DetectDetail detectDetail = detectDetails.get(0);
            String code = Optional.of(detectDetail).map(DetectDetail::getResultCode).orElse(Constants.DEFAULT_RESULT_CODE);
            vo.setResultCode(code);
            vo.setImageUrl(getFileUrl(detectDetail.getFilePath()));
            vo.setThumbnailImageUrl(getFileUrl(detectDetail.getThumbnailPath()));
            vo.setFilename(FilenameUtils.getName(vo.getImageUrl()));
        }
        String format = formatDate(detect.getCreateTime());
        vo.setCreateTimeStr(format);
        return vo;
    }

    @Override
    public ProDetectVO uploadDetect(UploadDetectDTO dto) throws IOException {
        String userName = SessionUtils.getUserName();
        // 保存到数据库中的相对路径
        String uploadRelativePath = "/" + userName+ "/detect";
        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        // 文件上传的最终保存路径
        String finalFilePath = "";
        String thumbRelativePath = "";
        try {
            String fileName = dto.getFile().getOriginalFilename();
            String extension = FilenameUtils.getExtension(fileName);
            fileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + extension;
            if (StringUtils.isNotBlank(fileName)) {
                finalFilePath = dynamicConfig.getFileBasePath() + uploadRelativePath + "/" + fileName;
                // 设置数据库保存的路径

                File outFile = new File(finalFilePath);
                if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
                    // 创建父文件夹
                    outFile.getParentFile().mkdirs();
                }
                fileOutputStream = new FileOutputStream(outFile);
                inputStream = dto.getFile().getInputStream();
                IOUtils.copy(inputStream, fileOutputStream);
                thumbRelativePath = saveThumb(finalFilePath, uploadRelativePath, fileName);

                uploadRelativePath += ("/" + fileName);
            }
        } catch (Exception e) {
            log.error("文件上传出错，userName:[{}], e:[{}]", userName, e);
            throw new CustomException("文件上传出错");
        } finally {
            if (fileOutputStream != null) {
                fileOutputStream.flush();
                fileOutputStream.close();
            }
        }

        // TODO 处理图片鉴真

        //写入数据库中,此时没有鉴真数据，写入默认数据
        ProDetect detect = new ProDetect();
        detect.setSourceType(dto.getSourceType());
        detect.setOperatorType(Constants.WECHAT_OPERATION_TYPE);
        detect.setTotalNum(1);
        detect.setTrueNum(0);
        detect.setFalseNum(0);
        DetectDetail detectDetail = new DetectDetail();
        //数据库中存储绝对路径
        detectDetail.setFilePath(dynamicConfig.getFileBasePath() + uploadRelativePath);
        detectDetail.setThumbnailPath(dynamicConfig.getFileBasePath() + thumbRelativePath);
        detectDetail.setResultCode(Constants.DEFAULT_RESULT_CODE);
        List<DetectDetail> list = Collections.singletonList(detectDetail);
        DetectInfo detectInfo = DetectInfo.builder().all(list).build();
        detect.setDetectInfo(JsonUtils.objectToJson(detectInfo));
        detect.setCreateBy(userName);
        Date now = new Date();
        detect.setCreateTime(now);
        detect.setUpdateBy(userName);
        detect.setUpdateTime(now);
        proDetectMapper.insertSelective(detect);

        ProDetectVO vo = new ProDetectVO();
        vo.setDetectId(detect.getDetectId());
        vo.setFilename(FilenameUtils.getName(finalFilePath));
        vo.setResultCode(Constants.DEFAULT_RESULT_CODE);
        vo.setCreateTimeStr(formatDate(now));
        vo.setImageUrl( dynamicConfig.getFileBaseUrl() + finalFilePath);
        vo.setThumbnailImageUrl(dynamicConfig.getFileBaseUrl() + thumbRelativePath);
        return vo;
    }

    private String saveThumb(String finalFilePath, String uploadPathDB, String fileName) throws IOException {
        String thumbFilePath = dynamicConfig.getFileBasePath() + uploadPathDB + "/thumb/" + fileName;
        File outFile = new File(thumbFilePath);
        if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
            outFile.getParentFile().mkdirs();
        }
        Thumbnails.of(finalFilePath).size(750, 1280).toFile(thumbFilePath);
        return uploadPathDB + "/thumb/" + fileName;
    }

    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        return sdf.format(date);
    }

    private List<ProDetectVO> getMappingVOs(List<ProDetect> proDetects) {
        if (proDetects == null || proDetects.isEmpty()) {
            return Collections.emptyList();
        }
        List<ProDetectVO> vos = new ArrayList<>();
        for (ProDetect detect : proDetects) {
             ProDetectVO vo = new ProDetectVO();
            vo.setDetectId(detect.getDetectId());
            DetectInfo detectInfo = JsonUtils.jsonToPojo(detect.getDetectInfo(), DetectInfo.class);
            if (detectInfo == null || CollectionUtils.isEmpty(detectInfo.getAll())) {
                continue;
            }
            DetectDetail detectDetail = detectInfo.getAll().get(0);
            if (detectDetail == null) {
                continue;
            }
            String code = Optional.ofNullable(detectDetail.getResultCode()).orElse(Constants.DEFAULT_RESULT_CODE);
            vo.setResultCode(code);
            vo.setImageUrl(getFileUrl(detectDetail.getFilePath()));
            vo.setThumbnailImageUrl(getFileUrl(detectDetail.getThumbnailPath()));
            vo.setCreateTimeStr(formatDate(detect.getCreateTime()));
            vos.add(vo);
        }
        return vos;
    }

    private String getFileUrl(String filePath) {
        if (StringUtils.isEmpty(filePath)) {
            return StringUtils.EMPTY;
        }
        return filePath.replace(dynamicConfig.getFileBasePath(), dynamicConfig.getFileBaseUrl());
    }
}

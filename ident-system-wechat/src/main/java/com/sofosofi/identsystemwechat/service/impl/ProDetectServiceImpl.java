package com.sofosofi.identsystemwechat.service.impl;

import com.github.pagehelper.PageHelper;
import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import com.sofosofi.identsystemwechat.common.Constants;
import com.sofosofi.identsystemwechat.common.CustomException;
import com.sofosofi.identsystemwechat.common.DetectResultTypeEnum;
import com.sofosofi.identsystemwechat.common.config.Config;
import com.sofosofi.identsystemwechat.common.protocol.IdentTypeEnum;
import com.sofosofi.identsystemwechat.common.protocol.dto.ProDetectDetailDTO;
import com.sofosofi.identsystemwechat.common.protocol.dto.ProDetectQueryPageDTO;
import com.sofosofi.identsystemwechat.common.protocol.dto.UploadDetectDTO;
import com.sofosofi.identsystemwechat.common.protocol.vo.ProDetectVO;
import com.sofosofi.identsystemwechat.common.protocol.vo.StatisticsVO;
import com.sofosofi.identsystemwechat.entity.*;
import com.sofosofi.identsystemwechat.mapper.ProDetectMapper;
import com.sofosofi.identsystemwechat.service.IDetectService;
import com.sofosofi.identsystemwechat.service.IProDetectService;
import com.sofosofi.identsystemwechat.utils.JsonUtils;
import com.sofosofi.identsystemwechat.utils.SessionUtils;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.checkerframework.checker.nullness.Opt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProDetectServiceImpl implements IProDetectService {

    @Autowired
    private Config config;

    @Resource
    private ProDetectMapper proDetectMapper;

    @Resource
    private IDetectService detectService;

    @Override
    public List<ProDetectVO> queryProDetectPage(ProDetectQueryPageDTO dto) {
        Example example = new Example(ProDetect.class);
        Example.Criteria criteria = example.createCriteria();
        if (dto.getIdentType().equals(IdentTypeEnum.ORIGINAL.getCode())) {
            criteria.andGreaterThanOrEqualTo("trueNum", 1);
        } else if (IdentTypeEnum.FAKED.getCode().equals(dto.getIdentType())){
            criteria.andGreaterThanOrEqualTo("falseNum", 1);
        }
        criteria.andEqualTo("createBy", SessionUtils.getUserName());
        criteria.andEqualTo("operatorType", Constants.WECHAT_OPERATION_TYPE);
        example.orderBy("createTime").desc();
        PageHelper.startPage(dto.getPage(), dto.getSize());
        List<ProDetect> proDetects = proDetectMapper.selectByExample(example);
        return getMappingVOs(proDetects);
    }

    @Override
    public ProDetectVO queryProDetectDetail(ProDetectDetailDTO dto) {
        ProDetect query = new ProDetect();
        query.setDetectId(dto.getDetectId());
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
                finalFilePath = config.getFileBasePath() + uploadRelativePath + "/" + fileName;
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

        DetectRes detectRes = new DetectRes();
        try {
            detectRes = detectService.detect(finalFilePath, dto.getFont());
        } catch (Exception e) {
            detectRes.setRetValue(Constants.DEFAULT_RESULT_CODE);
            detectRes.setRetDesc("鉴真处理异常，请稍后重试!");
            log.error("鉴真检测异常：finalFilePath:{}, e:{}", finalFilePath, Throwables.getStackTraceAsString(e));
        }

        //写入数据库中,此时没有鉴真数据，写入默认数据
        ProDetect detect = new ProDetect();
        detect.setSourceType(dto.getSourceType());
        detect.setOperatorType(Constants.WECHAT_OPERATION_TYPE);
        detect.setTotalNum(1);
        String code = Optional.ofNullable(detectRes).map(DetectRes::getRetValue)
                .filter(DetectResultTypeEnum.getCodeList()::contains).orElse(Constants.DEFAULT_RESULT_CODE);
        String retMsg = Optional.ofNullable(detectRes).map(DetectRes::getRetDesc).orElse(StringUtils.EMPTY);
        detect.setTrueNum(DetectResultTypeEnum.TRUE_FLAG.getRetValue().equals(code) ? 1 : 0);
        detect.setFalseNum(DetectResultTypeEnum.FALSE_FLAG.getRetValue().equals(code) ? 1 : 0);
        DetectDetail detectDetail = new DetectDetail();
        //数据库中存储绝对路径
        detectDetail.setFilePath(config.getFileBasePath() + uploadRelativePath);
        detectDetail.setThumbnailPath(config.getFileBasePath() + thumbRelativePath);
        detectDetail.setResultCode(code);
        detectDetail.setResultMsg(retMsg);
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
        vo.setCreateBy(userName);
        vo.setDetectId(detect.getDetectId());
        vo.setFilename(FilenameUtils.getName(finalFilePath));
        vo.setResultCode(code);
        vo.setCreateTimeStr(formatDate(now));
        vo.setImageUrl( config.getFileBaseUrl() + finalFilePath);
        vo.setThumbnailImageUrl(config.getFileBaseUrl() + thumbRelativePath);
        vo.setMsg(retMsg);
        return vo;
    }

    @Override
    public StatisticsVO statistics() {
       String userName = SessionUtils.getUserName();
       ProDetect detect = proDetectMapper.selectSumByUserName(userName);
       if (detect == null) {
           return new StatisticsVO();
       }
       StatisticsVO vo = new StatisticsVO();
       vo.setTotal(detect.getTotalNum());
       vo.setOriginal(detect.getTrueNum());
       vo.setFaked(detect.getFalseNum());
       return vo;
    }

    private String saveThumb(String finalFilePath, String uploadPathDB, String fileName) throws IOException {
        String thumbFilePath = config.getFileBasePath() + uploadPathDB + "/thumb/" + fileName;
        File outFile = new File(thumbFilePath);
        if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
            outFile.getParentFile().mkdirs();
        }
        Thumbnails.of(finalFilePath).size(750, 1280).toFile(thumbFilePath);
        return uploadPathDB + "/thumb/" + fileName;
    }

    private String formatDate(Date date) {
        return DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
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
        return filePath.replace(config.getFileBasePath(), config.getFileBaseUrl());
    }
}

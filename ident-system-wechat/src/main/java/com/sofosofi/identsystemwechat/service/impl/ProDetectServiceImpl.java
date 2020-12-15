package com.sofosofi.identsystemwechat.service.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.github.pagehelper.Page;
import com.sofosofi.identsystemwechat.common.Constants;
import com.sofosofi.identsystemwechat.common.CustomException;
import com.sofosofi.identsystemwechat.common.protocol.SofoJSONResult;
import com.sofosofi.identsystemwechat.common.protocol.dto.ProDetectDetailDTO;
import com.sofosofi.identsystemwechat.common.protocol.dto.ProDetectQueryPageDTO;
import com.sofosofi.identsystemwechat.common.protocol.dto.UploadDetectDTO;
import com.sofosofi.identsystemwechat.common.protocol.vo.ProDetectVO;
import com.sofosofi.identsystemwechat.entity.ProDetect;
import com.sofosofi.identsystemwechat.mapper.ProDetectMapper;
import com.sofosofi.identsystemwechat.service.IProDetectService;
import com.sofosofi.identsystemwechat.utils.JsonUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

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

    @Resource
    private ProDetectMapper proDetectMapper;

    @Override
    public List<ProDetect> queryUserList(Integer userId) {
        Example example = new Example.Builder(ProDetect.class).build();
        example.createCriteria().andEqualTo("createBy", userId);
        return proDetectMapper.selectByExample(example);
    }

    @Override
    public List<ProDetectVO> queryProDetectPage(ProDetectQueryPageDTO dto) {
        ProDetect query = new ProDetect();
        query.setCreateBy(dto.getUserName());
        List<ProDetect> proDetects =
                proDetectMapper.selectByRowBounds(query, new RowBounds(dto.getPage(), dto.getSize()));
        return getMappingVOs(proDetects);
    }

    @Override
    public ProDetectVO queryProDetectDetail(ProDetectDetailDTO dto) {
        ProDetect query = new ProDetect();
        query.setDetectId(dto.getProDetectId());
        query.setCreateBy(dto.getUserName());
        ProDetect detect = proDetectMapper.selectOne(query);
        if (detect == null) {
            throw new CustomException("鉴真记录不存在!");
        }
        List<DetectInfo> detectInfos = JsonUtils.jsonToList(detect.getDetectInfo(), DetectInfo.class);
        ProDetectVO vo = new ProDetectVO();
        if (CollectionUtils.isNotEmpty(detectInfos)) {
            DetectInfo detectInfo = detectInfos.get(0);
            Integer code = Optional.of(detectInfo).map(DetectInfo::getResultCode).map(Integer::parseInt).orElse(-1);
            vo.setResultCode(code);
            vo.setFileUrl(detectInfo.getFilePath());
            vo.setFilename(FilenameUtils.getName(vo.getFileUrl()));
        }
        String format = formatDate(detect.getCreateTime());
        vo.setCreateTimeStr(format);
        return vo;
    }

    @Override
    public ProDetectVO uploadDetect(UploadDetectDTO dto) throws IOException {
        String userName = dto.getUserName();
        // 文件保存的命名空间
        String fileSpace = "C:/sofosofi";
        // 保存到数据库中的相对路径
        String uploadPathDB = "/" + userName+ "/detect";

        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        // 文件上传的最终保存路径
        String finalFilePath = "";
        try {
            String fileName = dto.getFile().getOriginalFilename();
            // abc.mp4
            String arrayFilenameItem[] =  fileName.split("\\.");
            String fileNamePrefix = "";
            for (int i = 0 ; i < arrayFilenameItem.length-1 ; i ++) {
                fileNamePrefix += arrayFilenameItem[i];
            }

            if (StringUtils.isNotBlank(fileName)) {
                finalFilePath = Constants.FILE_SPACE + uploadPathDB + "/" + fileName;
                // 设置数据库保存的路径
                uploadPathDB += ("/" + fileName);

                File outFile = new File(finalFilePath);
                if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
                    // 创建父文件夹
                    outFile.getParentFile().mkdirs();
                }

                fileOutputStream = new FileOutputStream(outFile);
                inputStream = dto.getFile().getInputStream();
                IOUtils.copy(inputStream, fileOutputStream);
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

        //写入数据库中
        ProDetect detect = new ProDetect();
        detect.setSourceType(dto.getSourceType());
        detect.setOperatorType("1");
        detect.setTotalNum(1);
        detect.setTrueNum(1);
        detect.setFalseNum(0);
        DetectInfo detectInfo = new DetectInfo();
        detectInfo.setFilePath(finalFilePath);
        detect.setDetectInfo(JsonUtils.objectToJson(detectInfo));
        detect.setCreateBy(userName);
        Date now = new Date();
        detect.setCreateTime(now);
        detect.setUpdateBy(userName);
        detect.setUpdateTime(now);
        proDetectMapper.insertSelective(detect);

        ProDetectVO vo = new ProDetectVO();
        vo.setFilename(FilenameUtils.getName(finalFilePath));
        vo.setFileUrl(finalFilePath);
        vo.setCreateTimeStr(formatDate(now));
        vo.setResultCode(1);
        return vo;
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
            List<DetectInfo> detectInfos = JsonUtils.jsonToList(detect.getDetectInfo(), DetectInfo.class);
            if (CollectionUtils.isNotEmpty(detectInfos)) {
                Integer code =
                        Optional.of(detectInfos.get(0)).map(DetectInfo::getResultCode).map(Integer::parseInt).orElse(-1);
                vo.setResultCode(code);
            }
            String format = formatDate(detect.getCreateTime());
            vo.setCreateTimeStr(format);
        }
        return vos;
    }


    @Data
    static class DetectInfo {
        private String resultCode;

        private String filePath;

        private String resultMsg;
    }
}

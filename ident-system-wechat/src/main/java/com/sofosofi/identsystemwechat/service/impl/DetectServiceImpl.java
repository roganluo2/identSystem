package com.sofosofi.identsystemwechat.service.impl;

import com.sofosofi.identsystemwechat.common.CustomException;
import com.sofosofi.identsystemwechat.common.config.Config;
import com.sofosofi.identsystemwechat.entity.DetectRes;
import com.sofosofi.identsystemwechat.service.IDetectService;
import com.sofosofi.identsystemwechat.utils.ini.FastIni;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DetectServiceImpl implements IDetectService {

    @Value("${vmdetect.password:}")
    private String detectPassword;

    private String DEFAULT_FONT = "仿宋";

    @Autowired
    private Config config;

    @Override
    public DetectRes detect(String inputFile, String font) throws Exception{
        //	vmdetect ../samples/仿宋_sysfFS/未知作业名称.png 仿宋 output.file csmm666666
        List<String> command = new ArrayList<>();
        command.add(config.getVmdetect());
        File vmFile = new File(config.getVmdetect());
        if (!vmFile.exists()) {
            throw new CustomException("鉴真处理程序vmdetect不存在");
        }
        command.add(inputFile);
        command.add(Optional.ofNullable(font).orElse(DEFAULT_FONT));
        String path = FilenameUtils.getFullPath(inputFile);
        String outFilePath = path + "ini/" + FilenameUtils.getBaseName(inputFile) + ".ini";
        File outFile = new File(outFilePath);
        if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
            // 创建父文件夹
            outFile.getParentFile().mkdirs();
        }
        command.add(outFilePath);

        command.add(detectPassword);
        String fullCommand = StringUtils.join(command, " ");
        log.info("detect command：fullCommand:{}", fullCommand);

        ProcessBuilder builder = new ProcessBuilder(command);
        Process process = builder.start();

        BufferedReader br = null;
        InputStream errorStream = null;
        InputStreamReader inputStreamReader = null;

        try {
            errorStream = process.getErrorStream();
            inputStreamReader = new InputStreamReader(errorStream);
            br = new BufferedReader(inputStreamReader);
            String line = "";
            while ( (line = br.readLine()) != null ) {
                log.info("vmdetect line:{}", line);
            }
        } finally {
            if (br != null) {
                br.close();
            }
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            if (errorStream != null) {
                errorStream.close();
            }
        }
        FastIni fastIni = new FastIni();
        if (!outFile.exists()) {
            log.error("鉴真文件ini为空：path:{}", outFilePath);
            throw new CustomException("鉴真结果为空");
        }
        DetectRes detectRes = fastIni.fromPath(outFilePath, DetectRes.class);;
        return detectRes;
    }
}

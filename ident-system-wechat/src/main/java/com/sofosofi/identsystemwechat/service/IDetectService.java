package com.sofosofi.identsystemwechat.service;

import com.sofosofi.identsystemwechat.entity.DetectRes;

public interface IDetectService {

    /**
     * 执行文件鉴真，并返回鉴真结果
     * @param inputFile
     * @return
     */
    public DetectRes detect(String inputFile) throws Exception;


}

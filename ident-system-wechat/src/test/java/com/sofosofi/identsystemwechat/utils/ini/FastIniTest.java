package com.sofosofi.identsystemwechat.utils.ini;

import com.sofosofi.identsystemwechat.entity.DetectRes;
import com.sofosofi.identsystemwechat.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class FastIniTest {

    @Test
    public void testIniParse(){
        String path = "/Users/tanni/Desktop/rogan/ini/1.ini";
        FastIni fastIni = new FastIni();
        DetectRes detectRes = fastIni.fromPath(path, DetectRes.class);
        log.info("detectRes:{}", JsonUtils.objectToJson(detectRes));
    }

}

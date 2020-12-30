package com.sofosofi.identsystemwechat.utils;

import org.apache.commons.io.FilenameUtils;
import org.junit.Test;

public class FilenameUtilTests {

    @Test
    public void testGetName() {
        String name = "/Users/tanni/Desktop/rogan/upload/sofosofi/detect/e1d2064bb22c44f296b9202cb27bac80.jpeg";
        String baseName = FilenameUtils.getBaseName(name);
        System.out.println(baseName);
        String path = FilenameUtils.getFullPath(name);
        System.out.println(path);
    }

}

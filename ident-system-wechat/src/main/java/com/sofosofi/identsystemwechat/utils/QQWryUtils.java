package com.sofosofi.identsystemwechat.utils;

import com.github.jarod.qqwry.IPZone;
import com.github.jarod.qqwry.QQWry;

public class QQWryUtils {


    /**
     * 根据ip地址获取定位信息
     * @param ip
     * @return
     * @throws Exception
     */
    public static String getLocation(String ip) throws Exception{
        QQWry qqwry = new QQWry();
        IPZone ipzone = qqwry.findIP(ip);
        return ipzone.getMainInfo();
    }


}

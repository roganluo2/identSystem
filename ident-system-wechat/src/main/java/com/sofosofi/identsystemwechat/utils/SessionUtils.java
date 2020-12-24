package com.sofosofi.identsystemwechat.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

/**
 * @Description 通过theadLocal存储当前登录的用户信息
 * @Date 2020/12/22
 * @Created by rogan.luo
 */
public final class SessionUtils {

    private final static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void setUserName(String userName) {
        threadLocal.set(userName);
    }

    public static String getUserName() {
        return Optional.ofNullable(threadLocal.get()).orElse(StringUtils.EMPTY);
    }

}

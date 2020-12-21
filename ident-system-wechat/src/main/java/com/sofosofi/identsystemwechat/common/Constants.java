package com.sofosofi.identsystemwechat.common;

/**
 * 常量
 */
public final class Constants {

    /**
     * 微信接口调用成功的返回码
     */
    public static final Integer SUCCESS = 0;

    /**
     * 系统状态正常
     */
    public static final String SYS_STATUS_NORMAL = "0";


    /**
     * 文件保存的命名空间
     */
    public static final String FILE_SPACE = "C:/sofosofi";

    /**
     * redis用户session存储  prefix:userName
     */
    public static final String USER_REDIS_SESSION = "user-redis-session:%s";

    /**
     * 请求头字段名- userName
     */
    public static final String HEADER_USER_NAME = "headerUserName";

    /**
     * 请求头字段名- token
     */
    public static final String HEADER_USER_TOKEN = "headerUserToken";



}

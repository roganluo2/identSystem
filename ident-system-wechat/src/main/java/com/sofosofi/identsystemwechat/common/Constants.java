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
     * 系统状态异常
     */
    public static final String SYS_STATUS_ERROR = "1";


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

    /**
     * 状态正常标识
     */
    public static final String STATUS_NORMAL = "0";


    /**
     * 标记未读状态
     */
    public static final String NOT_READ = "N";

    /**
     * 标记终端，微信小程序
     */
    public static final String WECHAT_OPERATION_TYPE = "3";

    /**
     * 默认鉴真结果
     */
    public static final String DEFAULT_RESULT_CODE = "-1";

    public static final String HEAD_MODEL = "model";

    public static final String HEAD_PLATFORM = "platform";

    public static final String HEAD_WECHAT = "wechat";

    /**
     * 获取系统版本的key
     */
    public static final String HEADER_OS_KEY = "system";
}

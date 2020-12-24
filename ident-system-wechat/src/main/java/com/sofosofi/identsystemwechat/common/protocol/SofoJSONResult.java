package com.sofosofi.identsystemwechat.common.protocol;

import com.sofosofi.identsystemwechat.common.ReminderEnum;

/**
 * @Description: 自定义响应数据结构
 * 				这个类是提供给门户，ios，安卓，微信商城用的
 * 				门户接受此类数据后需要使用本类的方法转换成对于的数据类型格式（类，或者list）
 * 				其他自行处理
 * 				200：表示成功
 * 				500：表示错误，错误信息在msg字段中
 * 				502：拦截器拦截到用户token出错
 */
public class SofoJSONResult<T> {

    public static final Integer SUCCESS = 200;

    public static final Integer DEFAULT_FAIL = 500;

    // 响应业务状态
    private Integer status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private T data;

    public static <T>SofoJSONResult ok(T data) {
        return new SofoJSONResult<T>(data);
    }

    public static SofoJSONResult errorMsg(String msg) {
        return new SofoJSONResult(DEFAULT_FAIL, msg);
    }

    public static SofoJSONResult errorToken() {
        return new SofoJSONResult(ReminderEnum.NOT_TOKEN_ERROR.getCode(), ReminderEnum.NOT_TOKEN_ERROR.getMsg());
    }

    public static SofoJSONResult errorMsg(Integer status, String msg) {
        return new SofoJSONResult(status, msg);
    }

    public static SofoJSONResult success() {
        return new SofoJSONResult<Void>(null);
    }

    public SofoJSONResult() {

    }

    public SofoJSONResult(Integer status, String msg) {
        this.msg = msg;
        this.status = status;
    }

    public SofoJSONResult(Integer status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public SofoJSONResult(T data) {
        this(SUCCESS, "OK", data);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}

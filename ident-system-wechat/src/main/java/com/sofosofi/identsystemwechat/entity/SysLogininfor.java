package com.sofosofi.identsystemwechat.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_logininfor")
public class SysLogininfor {
    /**
     * 访问ID
     */
    @Id
    @Column(name = "info_id")
    private Long infoId;

    /**
     * 用户账号
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 登录IP地址
     */
    private String ipaddr;

    /**
     * 登录地点
     */
    @Column(name = "login_location")
    private String loginLocation;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 登录状态（0成功 1失败）
     */
    private String status;

    /**
     * 数据来源终端
     */
    @Column(name = "operator_type")
    private String operatorType;

    /**
     * 提示消息
     */
    private String msg;

    /**
     * 访问时间
     */
    @Column(name = "login_time")
    private Date loginTime;

    /**
     * 获取访问ID
     *
     * @return info_id - 访问ID
     */
    public Long getInfoId() {
        return infoId;
    }

    /**
     * 设置访问ID
     *
     * @param infoId 访问ID
     */
    public void setInfoId(Long infoId) {
        this.infoId = infoId;
    }

    /**
     * 获取用户账号
     *
     * @return user_name - 用户账号
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户账号
     *
     * @param userName 用户账号
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取登录IP地址
     *
     * @return ipaddr - 登录IP地址
     */
    public String getIpaddr() {
        return ipaddr;
    }

    /**
     * 设置登录IP地址
     *
     * @param ipaddr 登录IP地址
     */
    public void setIpaddr(String ipaddr) {
        this.ipaddr = ipaddr;
    }

    /**
     * 获取登录地点
     *
     * @return login_location - 登录地点
     */
    public String getLoginLocation() {
        return loginLocation;
    }

    /**
     * 设置登录地点
     *
     * @param loginLocation 登录地点
     */
    public void setLoginLocation(String loginLocation) {
        this.loginLocation = loginLocation;
    }

    /**
     * 获取浏览器类型
     *
     * @return browser - 浏览器类型
     */
    public String getBrowser() {
        return browser;
    }

    /**
     * 设置浏览器类型
     *
     * @param browser 浏览器类型
     */
    public void setBrowser(String browser) {
        this.browser = browser;
    }

    /**
     * 获取操作系统
     *
     * @return os - 操作系统
     */
    public String getOs() {
        return os;
    }

    /**
     * 设置操作系统
     *
     * @param os 操作系统
     */
    public void setOs(String os) {
        this.os = os;
    }

    /**
     * 获取登录状态（0成功 1失败）
     *
     * @return status - 登录状态（0成功 1失败）
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置登录状态（0成功 1失败）
     *
     * @param status 登录状态（0成功 1失败）
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取数据来源终端
     *
     * @return operator_type - 数据来源终端
     */
    public String getOperatorType() {
        return operatorType;
    }

    /**
     * 设置数据来源终端
     *
     * @param operatorType 数据来源终端
     */
    public void setOperatorType(String operatorType) {
        this.operatorType = operatorType;
    }

    /**
     * 获取提示消息
     *
     * @return msg - 提示消息
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 设置提示消息
     *
     * @param msg 提示消息
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 获取访问时间
     *
     * @return login_time - 访问时间
     */
    public Date getLoginTime() {
        return loginTime;
    }

    /**
     * 设置访问时间
     *
     * @param loginTime 访问时间
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }
}
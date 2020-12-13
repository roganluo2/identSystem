package com.sofosofi.identsystemwechat.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_oper_log")
public class SysOperLog {
    /**
     * 日志主键
     */
    @Id
    @Column(name = "oper_id")
    private Long operId;

    /**
     * 模块标题
     */
    private String title;

    /**
     * 业务类型（0其它 1新增 2修改 3删除）
     */
    @Column(name = "business_type")
    private Integer businessType;

    /**
     * 方法名称
     */
    private String method;

    /**
     * 请求方式
     */
    @Column(name = "request_method")
    private String requestMethod;

    /**
     * 操作类别（0 web后台管理系统 1 文件隐写客户端 2 文件鉴真客户端 3 微信小程序 4 手机端）
     */
    @Column(name = "operator_type")
    private Integer operatorType;

    /**
     * 操作人员
     */
    @Column(name = "oper_name")
    private String operName;

    /**
     * 部门名称
     */
    @Column(name = "dept_name")
    private String deptName;

    /**
     * 请求URL
     */
    @Column(name = "oper_url")
    private String operUrl;

    /**
     * 主机地址
     */
    @Column(name = "oper_ip")
    private String operIp;

    /**
     * 操作地点
     */
    @Column(name = "oper_location")
    private String operLocation;

    /**
     * 请求参数
     */
    @Column(name = "oper_param")
    private String operParam;

    /**
     * 返回参数
     */
    @Column(name = "json_result")
    private String jsonResult;

    /**
     * 操作状态（0正常 1异常）
     */
    private Integer status;

    /**
     * 错误消息
     */
    @Column(name = "error_msg")
    private String errorMsg;

    /**
     * 操作时间
     */
    @Column(name = "oper_time")
    private Date operTime;

    /**
     * 操作人id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 部门id
     */
    @Column(name = "dept_id")
    private Long deptId;

    /**
     * 获取日志主键
     *
     * @return oper_id - 日志主键
     */
    public Long getOperId() {
        return operId;
    }

    /**
     * 设置日志主键
     *
     * @param operId 日志主键
     */
    public void setOperId(Long operId) {
        this.operId = operId;
    }

    /**
     * 获取模块标题
     *
     * @return title - 模块标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置模块标题
     *
     * @param title 模块标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取业务类型（0其它 1新增 2修改 3删除）
     *
     * @return business_type - 业务类型（0其它 1新增 2修改 3删除）
     */
    public Integer getBusinessType() {
        return businessType;
    }

    /**
     * 设置业务类型（0其它 1新增 2修改 3删除）
     *
     * @param businessType 业务类型（0其它 1新增 2修改 3删除）
     */
    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    /**
     * 获取方法名称
     *
     * @return method - 方法名称
     */
    public String getMethod() {
        return method;
    }

    /**
     * 设置方法名称
     *
     * @param method 方法名称
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * 获取请求方式
     *
     * @return request_method - 请求方式
     */
    public String getRequestMethod() {
        return requestMethod;
    }

    /**
     * 设置请求方式
     *
     * @param requestMethod 请求方式
     */
    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    /**
     * 获取操作类别（0 web后台管理系统 1 文件隐写客户端 2 文件鉴真客户端 3 微信小程序 4 手机端）
     *
     * @return operator_type - 操作类别（0 web后台管理系统 1 文件隐写客户端 2 文件鉴真客户端 3 微信小程序 4 手机端）
     */
    public Integer getOperatorType() {
        return operatorType;
    }

    /**
     * 设置操作类别（0 web后台管理系统 1 文件隐写客户端 2 文件鉴真客户端 3 微信小程序 4 手机端）
     *
     * @param operatorType 操作类别（0 web后台管理系统 1 文件隐写客户端 2 文件鉴真客户端 3 微信小程序 4 手机端）
     */
    public void setOperatorType(Integer operatorType) {
        this.operatorType = operatorType;
    }

    /**
     * 获取操作人员
     *
     * @return oper_name - 操作人员
     */
    public String getOperName() {
        return operName;
    }

    /**
     * 设置操作人员
     *
     * @param operName 操作人员
     */
    public void setOperName(String operName) {
        this.operName = operName;
    }

    /**
     * 获取部门名称
     *
     * @return dept_name - 部门名称
     */
    public String getDeptName() {
        return deptName;
    }

    /**
     * 设置部门名称
     *
     * @param deptName 部门名称
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * 获取请求URL
     *
     * @return oper_url - 请求URL
     */
    public String getOperUrl() {
        return operUrl;
    }

    /**
     * 设置请求URL
     *
     * @param operUrl 请求URL
     */
    public void setOperUrl(String operUrl) {
        this.operUrl = operUrl;
    }

    /**
     * 获取主机地址
     *
     * @return oper_ip - 主机地址
     */
    public String getOperIp() {
        return operIp;
    }

    /**
     * 设置主机地址
     *
     * @param operIp 主机地址
     */
    public void setOperIp(String operIp) {
        this.operIp = operIp;
    }

    /**
     * 获取操作地点
     *
     * @return oper_location - 操作地点
     */
    public String getOperLocation() {
        return operLocation;
    }

    /**
     * 设置操作地点
     *
     * @param operLocation 操作地点
     */
    public void setOperLocation(String operLocation) {
        this.operLocation = operLocation;
    }

    /**
     * 获取请求参数
     *
     * @return oper_param - 请求参数
     */
    public String getOperParam() {
        return operParam;
    }

    /**
     * 设置请求参数
     *
     * @param operParam 请求参数
     */
    public void setOperParam(String operParam) {
        this.operParam = operParam;
    }

    /**
     * 获取返回参数
     *
     * @return json_result - 返回参数
     */
    public String getJsonResult() {
        return jsonResult;
    }

    /**
     * 设置返回参数
     *
     * @param jsonResult 返回参数
     */
    public void setJsonResult(String jsonResult) {
        this.jsonResult = jsonResult;
    }

    /**
     * 获取操作状态（0正常 1异常）
     *
     * @return status - 操作状态（0正常 1异常）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置操作状态（0正常 1异常）
     *
     * @param status 操作状态（0正常 1异常）
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取错误消息
     *
     * @return error_msg - 错误消息
     */
    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * 设置错误消息
     *
     * @param errorMsg 错误消息
     */
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     * 获取操作时间
     *
     * @return oper_time - 操作时间
     */
    public Date getOperTime() {
        return operTime;
    }

    /**
     * 设置操作时间
     *
     * @param operTime 操作时间
     */
    public void setOperTime(Date operTime) {
        this.operTime = operTime;
    }

    /**
     * 获取操作人id
     *
     * @return user_id - 操作人id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置操作人id
     *
     * @param userId 操作人id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取部门id
     *
     * @return dept_id - 部门id
     */
    public Long getDeptId() {
        return deptId;
    }

    /**
     * 设置部门id
     *
     * @param deptId 部门id
     */
    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }
}
package com.sofosofi.identsystemwechat.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_user_account")
public class SysUserAccount {
    /**
     * 用户登录ID
     */
    @Id
    @Column(name = "user_account_id")
    private Long userAccountId;

    /**
     * 用户账号 
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 快捷登录账号 微信openid 
     */
    @Column(name = "account_id")
    private String accountId;

    /**
     * 是否启用 sys_user_status 0正常 1停用
     */
    private String state;

    /**
     * 创建者
     */
    @Column(name = "create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新者
     */
    @Column(name = "update_by")
    private String updateBy;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 获取用户登录ID
     *
     * @return user_account_id - 用户登录ID
     */
    public Long getUserAccountId() {
        return userAccountId;
    }

    /**
     * 设置用户登录ID
     *
     * @param userAccountId 用户登录ID
     */
    public void setUserAccountId(Long userAccountId) {
        this.userAccountId = userAccountId;
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
     * 获取快捷登录账号 微信openid 
     *
     * @return account_id - 快捷登录账号 微信openid 
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * 设置快捷登录账号 微信openid 
     *
     * @param accountId 快捷登录账号 微信openid 
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    /**
     * 获取是否启用 sys_user_status 0正常 1停用
     *
     * @return state - 是否启用 sys_user_status 0正常 1停用
     */
    public String getState() {
        return state;
    }

    /**
     * 设置是否启用 sys_user_status 0正常 1停用
     *
     * @param state 是否启用 sys_user_status 0正常 1停用
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * 获取创建者
     *
     * @return create_by - 创建者
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * 设置创建者
     *
     * @param createBy 创建者
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新者
     *
     * @return update_by - 更新者
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * 设置更新者
     *
     * @param updateBy 更新者
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}
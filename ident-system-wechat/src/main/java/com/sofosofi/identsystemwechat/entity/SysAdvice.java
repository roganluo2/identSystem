package com.sofosofi.identsystemwechat.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_advice")
public class SysAdvice {
    /**
     * 意见反馈ID
     */
    @Id
    @Column(name = "advice_id")
    private Integer adviceId;

    /**
     * 意见标题
     */
    private String title;

    /**
     * 详细描述
     */
    private String describes;

    /**
     * 平台来源
     */
    @Column(name = "operator_type")
    private String operatorType;

    /**
     * 是否已读
     */
    @Column(name = "is_read")
    private String isRead;

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
     * 获取意见反馈ID
     *
     * @return advice_id - 意见反馈ID
     */
    public Integer getAdviceId() {
        return adviceId;
    }

    /**
     * 设置意见反馈ID
     *
     * @param adviceId 意见反馈ID
     */
    public void setAdviceId(Integer adviceId) {
        this.adviceId = adviceId;
    }

    /**
     * 获取意见标题
     *
     * @return title - 意见标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置意见标题
     *
     * @param title 意见标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取详细描述
     *
     * @return describes - 详细描述
     */
    public String getDescribes() {
        return describes;
    }

    /**
     * 设置详细描述
     *
     * @param describes 详细描述
     */
    public void setDescribes(String describes) {
        this.describes = describes;
    }

    /**
     * 获取平台来源
     *
     * @return operator_type - 平台来源
     */
    public String getOperatorType() {
        return operatorType;
    }

    /**
     * 设置平台来源
     *
     * @param operatorType 平台来源
     */
    public void setOperatorType(String operatorType) {
        this.operatorType = operatorType;
    }

    /**
     * 获取是否已读
     *
     * @return is_read - 是否已读
     */
    public String getIsRead() {
        return isRead;
    }

    /**
     * 设置是否已读
     *
     * @param isRead 是否已读
     */
    public void setIsRead(String isRead) {
        this.isRead = isRead;
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
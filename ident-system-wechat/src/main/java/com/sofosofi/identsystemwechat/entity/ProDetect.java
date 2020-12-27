package com.sofosofi.identsystemwechat.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "pro_detect")
public class ProDetect {
    /**
     * 鉴真ID
     */
    @Id
    @Column(name = "detect_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long detectId;

    /**
     * 文件来源
     */
    @Column(name = "source_type")
    private String sourceType;

    /**
     * 使用终端
     */
    @Column(name = "operator_type")
    private String operatorType;

    /**
     * 文件数量
     */
    @Column(name = "total_num")
    private Integer totalNum;

    /**
     * 真文件数
     */
    @Column(name = "true_num")
    private Integer trueNum;

    /**
     * 假文件数
     */
    @Column(name = "false_num")
    private Integer falseNum;

    /**
     * 鉴真结果信息（json格式）filePath 文件路径、resultCode 鉴真结果，resultMsg 结果信息
     */
    @Column(name = "detect_Info")
    private String detectInfo;

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
     * 获取鉴真ID
     *
     * @return detect_id - 鉴真ID
     */
    public Long getDetectId() {
        return detectId;
    }

    /**
     * 设置鉴真ID
     *
     * @param detectId 鉴真ID
     */
    public void setDetectId(Long detectId) {
        this.detectId = detectId;
    }

    /**
     * 获取文件来源
     *
     * @return source_type - 文件来源
     */
    public String getSourceType() {
        return sourceType;
    }

    /**
     * 设置文件来源
     *
     * @param sourceType 文件来源
     */
    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    /**
     * 获取使用终端
     *
     * @return operator_type - 使用终端
     */
    public String getOperatorType() {
        return operatorType;
    }

    /**
     * 设置使用终端
     *
     * @param operatorType 使用终端
     */
    public void setOperatorType(String operatorType) {
        this.operatorType = operatorType;
    }

    /**
     * 获取文件数量
     *
     * @return total_num - 文件数量
     */
    public Integer getTotalNum() {
        return totalNum;
    }

    /**
     * 设置文件数量
     *
     * @param totalNum 文件数量
     */
    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    /**
     * 获取真文件数
     *
     * @return true_num - 真文件数
     */
    public Integer getTrueNum() {
        return trueNum;
    }

    /**
     * 设置真文件数
     *
     * @param trueNum 真文件数
     */
    public void setTrueNum(Integer trueNum) {
        this.trueNum = trueNum;
    }

    /**
     * 获取假文件数
     *
     * @return false_num - 假文件数
     */
    public Integer getFalseNum() {
        return falseNum;
    }

    /**
     * 设置假文件数
     *
     * @param falseNum 假文件数
     */
    public void setFalseNum(Integer falseNum) {
        this.falseNum = falseNum;
    }

    /**
     * 获取鉴真结果信息（json格式）filePath 文件路径、resultCode 鉴真结果，resultMsg 结果信息
     *
     * @return detect_Info - 鉴真结果信息（json格式）filePath 文件路径、resultCode 鉴真结果，resultMsg 结果信息
     */
    public String getDetectInfo() {
        return detectInfo;
    }

    /**
     * 设置鉴真结果信息（json格式）filePath 文件路径、resultCode 鉴真结果，resultMsg 结果信息
     *
     * @param detectInfo 鉴真结果信息（json格式）filePath 文件路径、resultCode 鉴真结果，resultMsg 结果信息
     */
    public void setDetectInfo(String detectInfo) {
        this.detectInfo = detectInfo;
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
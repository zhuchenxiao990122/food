package com.xw.project.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xw.project.entity.DisDonationProjectDetail;

import java.util.Date;
import java.util.List;

/**
 * @author yuli
 * @since 2020-06-24
 */
public class DisDonationProjectVo {
    private String id;

    /**
     * 组织id
     */
    private String orgId;

    /**
     * 捐赠类型
     */
    private String type;

    /**
     * 项目编号
     */
    private String code;

    /**
     * 截至时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date deadline;

    /**
     * 项目名称
     */
    private String name;

    /**
     * 项目简介
     */
    private String briefIntro;

    /**
     * 详细介绍
     */
    private String detailIntro;

    /**
     * 状态
     */
    private String state;

    /**
     * 发布时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date publishTime;

    /**
     * 撤销时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date cancelTime;


    /**
     * 状态名称
     */
    private String stateName;

    /**
     * 申请时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date applyTime;

    List<DisDonationProjectDetail> disDonationProjectDetails;

    List<String> fileIds;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBriefIntro() {
        return briefIntro;
    }

    public void setBriefIntro(String briefIntro) {
        this.briefIntro = briefIntro;
    }

    public String getDetailIntro() {
        return detailIntro;
    }

    public void setDetailIntro(String detailIntro) {
        this.detailIntro = detailIntro;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Date getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public List<DisDonationProjectDetail> getDisDonationProjectDetails() {
        return disDonationProjectDetails;
    }

    public void setDisDonationProjectDetails(List<DisDonationProjectDetail> disDonationProjectDetails) {
        this.disDonationProjectDetails = disDonationProjectDetails;
    }

    public List<String> getFileIds() {
        return fileIds;
    }

    public void setFileIds(List<String> fileIds) {
        this.fileIds = fileIds;
    }
}
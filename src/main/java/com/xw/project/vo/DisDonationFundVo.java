package com.xw.project.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author yuli
 * @since 2020-06-24
 */
public class DisDonationFundVo {
    /**
     * 捐赠者
     */
    private String id;
    /**
     * 捐赠者
     */
    private String orgId;
    /**
     * 捐赠者
     */
    private String donor;

    /**
     * 捐赠者类型（个人/法人）
     */
    private String donorType;

    /**
     * 捐赠项目
     */
    private String projectId;

    /**
     * 是否匿名
     */
    private String anonymous;

    /**
     * 捐赠时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date donationTime;

    /**
     * 支付渠道（微信、支付宝、银行）
     */
    private String payChannel;

    /**
     * 支付编号
     */
    private String payCode;

    /**
     * 捐赠金额
     */
    private BigDecimal amount;

    /**
     * 手机号
     */
    private String phoneNumber;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 身份证号
     */
    private String idCard;
    /**
     * 是否生成票据
     */
    private String billApply;
    /**
     * 捐赠类型（个人/单位）
     */
    private String donorTypeName;
    /**
     * 是or否
     */
    private String anonymousName;
    /**
     * 捐赠渠道名称
     */
    private String payChannelName;
    /**
     * 是or否
     */
    private String billApplyName;
    /**
     * 项目名称
     */
    private String projectName;

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

    public String getDonor() {
        return donor;
    }

    public void setDonor(String donor) {
        this.donor = donor;
    }

    public String getDonorType() {
        return donorType;
    }

    public void setDonorType(String donorType) {
        this.donorType = donorType;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(String anonymous) {
        this.anonymous = anonymous;
    }

    public Date getDonationTime() {
        return donationTime;
    }

    public void setDonationTime(Date donationTime) {
        this.donationTime = donationTime;
    }

    public String getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(String payChannel) {
        this.payChannel = payChannel;
    }

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getBillApply() {
        return billApply;
    }

    public void setBillApply(String billApply) {
        this.billApply = billApply;
    }

    public String getDonorTypeName() {
        return donorTypeName;
    }

    public void setDonorTypeName(String donorTypeName) {
        this.donorTypeName = donorTypeName;
    }

    public String getAnonymousName() {
        return anonymousName;
    }

    public void setAnonymousName(String anonymousName) {
        this.anonymousName = anonymousName;
    }

    public String getPayChannelName() {
        return payChannelName;
    }

    public void setPayChannelName(String payChannelName) {
        this.payChannelName = payChannelName;
    }

    public String getBillApplyName() {
        return billApplyName;
    }

    public void setBillApplyName(String billApplyName) {
        this.billApplyName = billApplyName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
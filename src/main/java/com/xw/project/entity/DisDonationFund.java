package com.xw.project.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.xw.common.entity.BasePlusEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author yuli
 * @since 2020-07-16
 */
@TableName("dis_donation_fund")
public class DisDonationFund extends BasePlusEntity {

    private static final long serialVersionUID = 1L;
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
     * 身份证号
     */
    private String billApply;

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

    public static final String DONOR = "donor";

    public static final String DONOR_TYPE = "donor_type";

    public static final String PROJECT_ID = "project_id";

    public static final String ANONYMOUS = "anonymous";

    public static final String DONATION_TIME = "donation_time";

    public static final String PAY_CHANNEL = "pay_channel";

    public static final String PAY_CODE = "pay_code";

    public static final String AMOUNT = "amount";

    public static final String PHONE_NUMBER = "phone_number";

    public static final String EMAIL = "email";

    public static final String ID_CARD = "id_card";

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getBillApply() {
        return billApply;
    }

    public void setBillApply(String billApply) {
        this.billApply = billApply;
    }
}

package com.xw.project.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.xw.common.entity.BasePlusEntity;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author yuli
 * @since 2020-07-14
 */
@TableName("dis_donation_intention")
public class DisDonationIntention extends BasePlusEntity {

private static final long serialVersionUID=1L;

    /**
     * 捐赠项目
     */
    @NotNull
    private String projectId;
    @NotNull
    private String orgId;

    /**
     * 意向编号
     */
    private String code;

    /**
     * 捐赠者
     */
    @NotNull
    private String donor;

    /**
     * 国籍
     */
    private String nation;

    /**
     * 证书类型
     */
    private String certType;

    /**
     * 证书号码
     */
    private String certCode;

    /**
     * 捐赠所在区域
     */
    private String region;

    /**
     * 物资始发地
     */
    private String origin;

    /**
     * 物流方式
     */
    private String logisticsMode;

    /**
     * 是否匿名
     */
    private String anonymous;

    /**
     * 联系人
     */
    private String linkPerson;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 捐赠日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime donationTime;

    /**
     * 捐赠意向
     */
    private String intention;

    /**
     * 申请状态
     */
    private String applyStatus;

    /**
     * 捐赠金额总计
     */
    private String total;


    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDonor() {
        return donor;
    }

    public void setDonor(String donor) {
        this.donor = donor;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getCertType() {
        return certType;
    }

    public void setCertType(String certType) {
        this.certType = certType;
    }

    public String getCertCode() {
        return certCode;
    }

    public void setCertCode(String certCode) {
        this.certCode = certCode;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getLogisticsMode() {
        return logisticsMode;
    }

    public void setLogisticsMode(String logisticsMode) {
        this.logisticsMode = logisticsMode;
    }

    public String getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(String anonymous) {
        this.anonymous = anonymous;
    }

    public String getLinkPerson() {
        return linkPerson;
    }

    public void setLinkPerson(String linkPerson) {
        this.linkPerson = linkPerson;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getDonationTime() {
        return donationTime;
    }

    public void setDonationTime(LocalDateTime donationTime) {
        this.donationTime = donationTime;
    }

    public String getIntention() {
        return intention;
    }

    public void setIntention(String intention) {
        this.intention = intention;
    }

    public String getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(String applyStatus) {
        this.applyStatus = applyStatus;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public static final String PROJECT_ID = "project_id";

    public static final String ORG_ID = "org_id";

    public static final String CODE = "code";

    public static final String DONOR = "donor";

    public static final String NATION = "nation";

    public static final String CERT_TYPE = "cert_type";

    public static final String CERT_CODE = "cert_code";

    public static final String REGION = "region";

    public static final String ORIGIN = "origin";

    public static final String LOGISTICS_MODE = "logistics_mode";

    public static final String ANONYMOUS = "anonymous";

    public static final String LINK_PERSON = "link_person";

    public static final String PHONE = "phone";

    public static final String DONATION_TIME = "donation_time";

    public static final String INTENTION = "intention";

    public static final String APPLY_STATUS = "apply_status";

    public static final String TOTAL = "total";

    @Override
    public String toString() {
        return "OthDonationIntention{" +
        "projectId=" + projectId +
        ", orgId=" + orgId +
        ", code=" + code +
        ", donor=" + donor +
        ", nation=" + nation +
        ", certType=" + certType +
        ", certCode=" + certCode +
        ", region=" + region +
        ", origin=" + origin +
        ", logisticsMode=" + logisticsMode +
        ", anonymous=" + anonymous +
        ", linkPerson=" + linkPerson +
        ", phone=" + phone +
        ", donationTime=" + donationTime +
        ", intention=" + intention +
        ", applyStatus=" + applyStatus +
        ", total=" + total +
        "}";
    }
}

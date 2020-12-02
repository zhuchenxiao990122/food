package com.xw.project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xw.project.entity.DisDonationIntentionDetail;
import com.xw.project.util.PageHelper;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author yuli
 * @since 2020-07-14
 */
public class DisDonationIntentionDto extends PageHelper {

    private String projectId;
    private String orgId;
    private String code;
    private String donor;
    private String nation;
    private String certType;
    private String certCode;
    private String region;
    private String origin;
    private String logisticsMode;
    private String anonymous;
    private String linkPerson;
    private String phone;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date donationTime;
    private String intention;
    private String applyStatus;
    private String total;
    List<DisDonationIntentionDetail> disDonationIntentionDetails;
    List<Map<String,Object>> files;

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

    public Date getDonationTime() {
        return donationTime;
    }

    public void setDonationTime(Date donationTime) {
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

    public List<DisDonationIntentionDetail> getDisDonationIntentionDetails() {
        return disDonationIntentionDetails;
    }

    public void setDisDonationIntentionDetails(List<DisDonationIntentionDetail> disDonationIntentionDetails) {
        this.disDonationIntentionDetails = disDonationIntentionDetails;
    }

}
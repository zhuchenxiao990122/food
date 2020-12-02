package com.xw.project.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xw.project.entity.ComFile;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author yuli
 * @since 2020-07-14
 */
public class DisDonationIntentionVo {
    private String id;
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
    private String regionName;
    private String originName;
    private String logisticsModeName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date donationTime;
    private String intention;
    private String applyStatus;
    private String total;
    private String anonymousName;
    private List<Map> intentionDetails;
    private List<ComFile> fileIds;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public String getLogisticsModeName() {
        return logisticsModeName;
    }

    public void setLogisticsModeName(String logisticsModeName) {
        this.logisticsModeName = logisticsModeName;
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

    public String getAnonymousName() {
        return anonymousName;
    }

    public void setAnonymousName(String anonymousName) {
        this.anonymousName = anonymousName;
    }

    public List<Map> getIntentionDetails() {
        return intentionDetails;
    }

    public void setIntentionDetails(List<Map> intentionDetails) {
        this.intentionDetails = intentionDetails;
    }

    public List<ComFile> getFileIds() {
        return fileIds;
    }

    public void setFileIds(List<ComFile> fileIds) {
        this.fileIds = fileIds;
    }
}
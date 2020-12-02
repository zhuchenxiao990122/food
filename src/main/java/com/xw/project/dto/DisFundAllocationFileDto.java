package com.xw.project.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author yuli
 * @since 2020-07-23
 */
public class DisFundAllocationFileDto {
    private String id;
    private String orgId;
    private String projectId;
    private String code;
    private String reason;
    private BigDecimal amount;
    private String contactItemId;
    private List<String> fileIds;

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

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getContactItemId() {
        return contactItemId;
    }

    public void setContactItemId(String contactItemId) {
        this.contactItemId = contactItemId;
    }

    public List<String> getFileIds() {
        return fileIds;
    }

    public void setFileIds(List<String> fileIds) {
        this.fileIds = fileIds;
    }
}
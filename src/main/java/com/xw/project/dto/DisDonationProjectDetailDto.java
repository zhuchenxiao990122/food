package com.xw.project.dto;

import com.xw.project.entity.DisDonationProjectDetail;

import java.util.List;

/**
 * @author yuli
 * @since 2020-07-01
 */
public class DisDonationProjectDetailDto {

    private String projectId;

    private String donationType;

    private List<DisDonationProjectDetail> disDonationProjectDetail;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getDonationType() {
        return donationType;
    }

    public void setDonationType(String donationType) {
        this.donationType = donationType;
    }

    public List<DisDonationProjectDetail> getDisDonationProjectDetail() {
        return disDonationProjectDetail;
    }

    public void setDisDonationProjectDetail(List<DisDonationProjectDetail> disDonationProjectDetail) {
        this.disDonationProjectDetail = disDonationProjectDetail;
    }
}
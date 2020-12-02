package com.xw.project.dto;

import com.xw.project.entity.DisDonationIntentionDetail;

import java.util.List;

/**
 * @author yuli
 * @since 2020-07-14
 */
public class DisDonationIntentionDetailDto {

    private List<DisDonationIntentionDetail> othDonationIntentionDetails;

    private String intentionId;

    public List<DisDonationIntentionDetail> getOthDonationIntentionDetails() {
        return othDonationIntentionDetails;
    }

    public void setOthDonationIntentionDetails(List<DisDonationIntentionDetail> othDonationIntentionDetails) {
        this.othDonationIntentionDetails = othDonationIntentionDetails;
    }

    public String getIntentionId() {
        return intentionId;
    }

    public void setIntentionId(String intentionId) {
        this.intentionId = intentionId;
    }
}
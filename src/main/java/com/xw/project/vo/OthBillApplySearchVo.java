package com.xw.project.vo;

import com.xw.project.entity.PageEntity;

public class OthBillApplySearchVo extends PageEntity {
    private String phoneMumber;

    private String donationBillTitle;

    private String donationEvidence;

    public String getPhoneMumber() {
        return phoneMumber;
    }

    public void setPhoneMumber(String phoneMumber) {
        this.phoneMumber = phoneMumber;
    }

    public String getDonationBillTitle() {
        return donationBillTitle;
    }

    public void setDonationBillTitle(String donationBillTitle) {
        this.donationBillTitle = donationBillTitle;
    }

    public String getDonationEvidence() {
        return donationEvidence;
    }

    public void setDonationEvidence(String donationEvidence) {
        this.donationEvidence = donationEvidence;
    }
}

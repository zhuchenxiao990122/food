package com.xw.project.dto;

import com.xw.project.util.PageHelper;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author yuli
 * @since 2020-07-16
 */
public class DisDonationFundDto extends PageHelper {

    private String donor;
    private String donorType;
    private String projectId;
    private List<String> donationTime;
    private String payChannel;
    private String payCode;
    private BigDecimal amount;
    private String phoneNumber;
    private String email;
    private String idCard;

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

    public List<String> getDonationTime() {
        return donationTime;
    }

    public void setDonationTime(List<String> donationTime) {
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


}
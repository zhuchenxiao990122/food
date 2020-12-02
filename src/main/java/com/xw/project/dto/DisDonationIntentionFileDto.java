package com.xw.project.dto;

import java.util.List;

/**
 * @author yuli
 * @since 2020-07-14
 */
public class DisDonationIntentionFileDto {

    private List<String> certFile;
    private List<String> materialFile;
    private List<String> qualityCertFile;
    private List<String> estimatedValue;
    private List<String> otherFile;

    public List<String> getCertFile() {
        return certFile;
    }

    public void setCertFile(List<String> certFile) {
        this.certFile = certFile;
    }

    public List<String> getMaterialFile() {
        return materialFile;
    }

    public void setMaterialFile(List<String> materialFile) {
        this.materialFile = materialFile;
    }

    public List<String> getQualityCertFile() {
        return qualityCertFile;
    }

    public void setQualityCertFile(List<String> qualityCertFile) {
        this.qualityCertFile = qualityCertFile;
    }

    public List<String> getEstimatedValue() {
        return estimatedValue;
    }

    public void setEstimatedValue(List<String> estimatedValue) {
        this.estimatedValue = estimatedValue;
    }

    public List<String> getOtherFile() {
        return otherFile;
    }

    public void setOtherFile(List<String> otherFile) {
        this.otherFile = otherFile;
    }
}
package com.xw.project.dto;

import com.xw.project.entity.DisMaterialDonationRequire;;

import java.util.List;

/**
 * @author yuli
 * @since 2020-06-30
 */
public class DisMaterialDonationRequireMaterialDto {

    private String materialId;

    private List<DisMaterialDonationRequire> disMaterialDonationRequires;

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public List<DisMaterialDonationRequire> getDisMaterialDonationRequires() {
        return disMaterialDonationRequires;
    }

    public void setDisMaterialDonationRequires(List<DisMaterialDonationRequire> disMaterialDonationRequires) {
        this.disMaterialDonationRequires = disMaterialDonationRequires;
    }
}
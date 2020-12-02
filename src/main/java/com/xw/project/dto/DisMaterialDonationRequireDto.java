package com.xw.project.dto;

import com.xw.project.util.PageHelper;

/**
 * @author yuli
 * @since 2020-06-30
 */
public class DisMaterialDonationRequireDto extends PageHelper {

    private String materialId;
    private String require;


    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public String getRequire() {
        return require;
    }

    public void setRequire(String require) {
        this.require = require;
    }


}
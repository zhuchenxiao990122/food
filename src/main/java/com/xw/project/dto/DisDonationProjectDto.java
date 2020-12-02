package com.xw.project.dto;

import com.xw.project.util.PageHelper;

/**
 * @author yuli
 * @since 2020-06-24
 */
public class DisDonationProjectDto extends PageHelper {

    private String code;
    private String name;
    private String applyStartTime;
    private String applyEndTime;
    private String userId;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String title) {
        this.name = title;
    }

    public String getApplyStartTime() {
        return applyStartTime;
    }

    public void setApplyStartTime(String applyStartTime) {
        this.applyStartTime = applyStartTime;
    }

    public String getApplyEndTime() {
        return applyEndTime;
    }

    public void setApplyEndTime(String applyEndTime) {
        this.applyEndTime = applyEndTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
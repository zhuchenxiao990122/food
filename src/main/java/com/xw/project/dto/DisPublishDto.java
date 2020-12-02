package com.xw.project.dto;


import com.xw.project.util.PageHelper;
import lombok.Data;

@Data
public class DisPublishDto extends PageHelper {

    private String isDel;
    private String publishCode;
    private String receiveOrgId;
    private String disasterType;

}

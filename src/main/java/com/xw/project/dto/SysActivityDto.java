package com.xw.project.dto;

import com.xw.project.util.PageHelper;
import lombok.Data;

@Data
public class SysActivityDto extends PageHelper{
    private String activityName;
    private String activityType;
    private int Id;
}

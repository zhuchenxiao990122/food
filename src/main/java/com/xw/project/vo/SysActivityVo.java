package com.xw.project.vo;

import com.xw.project.entity.SysActivity;
import lombok.Data;
import java.io.Serializable;

@Data
public class SysActivityVo extends SysActivity implements Serializable {
    private String activityName;
    private String startDate;
    private String endDate;
    private String activityType;
    private int peopleNum;
}

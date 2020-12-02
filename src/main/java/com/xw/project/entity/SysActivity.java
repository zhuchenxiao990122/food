package com.xw.project.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xw.common.entity.BasePlusEntity;
import lombok.Data;

@Data
@TableName("sys_activity")
public class SysActivity extends BasePlusEntity{
    private String activityName;
    private String startDate;
    private String endDate;
    private String activityType;
    private int peopleNum;
    @Override
    public String toString() {
        return "SysActivity{" +
                ", activity_name=" + activityName +
                ", start_date=" + startDate +
                ", end_date=" + endDate +
                ", activity_type=" + activityType +
                ", people_num=" + peopleNum +
                "}";
    }
}

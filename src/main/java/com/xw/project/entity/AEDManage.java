package com.xw.project.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@TableName("rtm_aed")
public class AEDManage {

    private String id;

    //AED编号
    private String aedId;

    //安装地点
    private String installAddress;

    //经度
    private Double positionLon;

    //异常标志
    private String errorFlag;

    //纬度
    private Double positionLat;

    private Date updateDate;

    private Date createDate;

    private String updateBy;

    private String createBy;

    private String delFlag;
}

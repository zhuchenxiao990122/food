package com.xw.project.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xw.common.entity.BasePlusEntity;
import com.xw.project.util.PageHelper;
import lombok.Data;

/**
 * @author Jy
 * @since 2020-06-23
 */
@Data
public class RtmAedDto extends PageHelper {
    private String id;
    //安装时间
    private Date installDate;
    //类型
    private String model;
    //地址
    private String address;
    //经度
    private BigDecimal positionLon;
    //纬度
    private BigDecimal positionLat;
    //类型
    private String state;
    //行政区域编码
    private String areaId;
    //状态名
    private String stateName;
}
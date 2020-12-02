package com.xw.project.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class AdvRcParkVo {
    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 安装时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date installDate;

    /**
     * 安装地址
     */
    private String address;

    /**
     * 经度
     */
    private BigDecimal positionLon;

    /**
     * 纬度
     */
    private BigDecimal positionLat;

    /**
     * 状态
     */
    private String state;

    /**
     * 状态名
     */
    private String stateName;

    /**
     * 父级城市的ID
     */
    private String parentId;

    /**
     * 子级城市列表
     */
    private List<AdvRcParkVo> childrens;

    /**
     * 区域名称 例如杭州市 宁波市
     */
    private String areaName;

    /**
     *行政区域编码
     */
    private String areaId;

    /**
     * 所属组织名称
     */
    private String orgName;
}

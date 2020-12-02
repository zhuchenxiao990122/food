package com.xw.project.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author Jy
 * @since 2020-06-23
 */
@Data
public class RtmAedVo {

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
    private List<RtmAedVo> childrens;

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

    /**
     * AED型号
     */
    private String model;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getPositionLon() {
        return positionLon;
    }

    public void setPositionLon(BigDecimal positionLon) {
        this.positionLon = positionLon;
    }

    public BigDecimal getPositionLat() {
        return positionLat;
    }

    public void setPositionLat(BigDecimal positionLat) {
        this.positionLat = positionLat;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public List<RtmAedVo> getChildrens() {
        return childrens;
    }

    public void setChildrens(List<RtmAedVo> childrens) {
        this.childrens = childrens;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}

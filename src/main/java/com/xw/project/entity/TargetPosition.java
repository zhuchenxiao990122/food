package com.xw.project.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xw.common.entity.BasePlusEntity;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author Jy
 * @since 2020-06-28
 */
@TableName("target_position")
@Data
public class TargetPosition extends BasePlusEntity {

private static final long serialVersionUID=1L;

    private String id;

    /**
     * 关联ID
     */
    private String refId;

    /**
     * 关联类型(与字典表ID相同)
     */
    private String refType;

    /**
     * 所属组织ID(与sys_orgs关联ID)
     */
    private String orgId;

    /**
     * 行政区域编码(与sys_area关联)
     */
    private String areaId;

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
     * 存放字典表ID(关联sys_dict)
     */
    private String state;

    private Date updateDate;

    private Date createDate;

    private String updateBy;

    private String createBy;

    private String delFlag;

    private String remarks;


    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public String getRefType() {
        return refType;
    }

    public void setRefType(String refType) {
        this.refType = refType;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
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

    public static final String REF_ID = "ref_id";

    public static final String REF_TYPE = "ref_type";

    public static final String ORG_ID = "org_id";

    public static final String AREA_ID = "area_id";

    public static final String ADDRESS = "address";

    public static final String POSITION_LON = "position_lon";

    public static final String POSITION_LAT = "position_lat";

    public static final String STATE = "state";

    @Override
    public String toString() {
        return "TargetPosition{" +
        "refId=" + refId +
        ", refType=" + refType +
        ", orgId=" + orgId +
        ", areaId=" + areaId +
        ", address=" + address +
        ", positionLon=" + positionLon +
        ", positionLat=" + positionLat +
        ", state=" + state +
        "}";
    }
}

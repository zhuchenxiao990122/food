package com.xw.project.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xw.common.entity.BasePlusEntity;

/**
 * <p>
 * 
 * </p>
 *
 * @author caoshuhao
 * @since 2020-06-22
 */
@TableName("org_history")
public class OrgHistory extends BasePlusEntity {

private static final long serialVersionUID=1L;

    /**
     * 父组织主键
     */
    private String parentId;

    /**
     * 所有父组织
     */
    private String parentIds;

    /**
     * 届数
     */
    private String sessionNum;

    /**
     * 组织机构简称
     */
    private String name;

    /**
     * 组织机构全称
     */
    private String fullName;

    /**
     * 地区id
     */
    private String areaId;

    /**
     * 组织类型
     */
    private String orgType;

    /**
     * 组织机构代码证
     */
    private String unicode;

    /**
     * 行政等级
     */
    private String level;

    /**
     * 排序号
     */
    private String orderBy;

    /**
     * 组织编码
     */
    private String orgcoding;

    /**
     * 组织的唯一编码
     */
    private String uniqueCoding;

    private String devCoding;

    private String remarks;


    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public String getSessionNum() {
        return sessionNum;
    }

    public void setSessionNum(String sessionNum) {
        this.sessionNum = sessionNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public String getUnicode() {
        return unicode;
    }

    public void setUnicode(String unicode) {
        this.unicode = unicode;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrgcoding() {
        return orgcoding;
    }

    public void setOrgcoding(String orgcoding) {
        this.orgcoding = orgcoding;
    }

    public String getUniqueCoding() {
        return uniqueCoding;
    }

    public void setUniqueCoding(String uniqueCoding) {
        this.uniqueCoding = uniqueCoding;
    }

    public String getDevCoding() {
        return devCoding;
    }

    public void setDevCoding(String devCoding) {
        this.devCoding = devCoding;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public static final String PARENT_ID = "parent_id";

    public static final String PARENT_IDS = "parent_ids";

    public static final String SESSION_NUM = "session_num";

    public static final String NAME = "name";

    public static final String FULL_NAME = "full_name";

    public static final String AREA_ID = "area_id";

    public static final String ORG_TYPE = "org_type";

    public static final String UNICODE = "unicode";

    public static final String LEVEL = "level";

    public static final String ORDER_BY = "order_by";

    public static final String ORGCODING = "orgcoding";

    public static final String UNIQUE_CODING = "unique_coding";

    public static final String DEV_CODING = "dev_coding";

    public static final String REMARKS = "remarks";

    @Override
    public String toString() {
        return "OrgHistory{" +
        "parentId=" + parentId +
        ", parentIds=" + parentIds +
        ", sessionNum=" + sessionNum +
        ", name=" + name +
        ", fullName=" + fullName +
        ", areaId=" + areaId +
        ", orgType=" + orgType +
        ", unicode=" + unicode +
        ", level=" + level +
        ", orderBy=" + orderBy +
        ", orgcoding=" + orgcoding +
        ", uniqueCoding=" + uniqueCoding +
        ", devCoding=" + devCoding +
        ", remarks=" + remarks +
        "}";
    }
}

package com.xw.project.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xw.common.entity.BasePlusEntity;

import java.math.BigDecimal;

/**
 * <p>
 *
 * </p>
 *
 * @author yuli
 * @since 2020-07-01
 */
@TableName("dis_donation_project_detail")
public class DisDonationProjectDetail extends BasePlusEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 项目id
     */
    private String projectId;

    /**
     * 物资种类id
     */
    private String categoryId;

    private String materialName;

    /**
     * 目标数量
     */
    private BigDecimal targetNum;

    /**
     * 最低数量
     */
    private BigDecimal miniNum;

    /**
     * 单位
     */
    private String unit;

    /**
     * 类型（捐款捐物）
     */
    private String type;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public BigDecimal getMiniNum() {
        return miniNum;
    }

    public void setMiniNum(BigDecimal miniNum) {
        this.miniNum = miniNum;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getTargetNum() {
        return targetNum;
    }

    public void setTargetNum(BigDecimal targetNum) {
        this.targetNum = targetNum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

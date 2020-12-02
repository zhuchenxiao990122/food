package com.xw.project.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xw.common.entity.BasePlusEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 *
 * </p>
 *
 * @author caoshuhao
 * @since 2020-06-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("dis_rescue_request")
public class DisRescueRequest extends BasePlusEntity {

private static final long serialVersionUID=1L;

    private String publishId;

    /**
     * 物资类别
     */
    private String categoryId;

    /**
     * 申请编号
     */
    private String applyNumber;

    /**
     * 物资名称
     */
    private String materialName;

    /**
     * 型号/规格
     */
    private String model;

    /**
     * 单位
     */
    private String unit;

    /**
     * 单价
     */
    private BigDecimal unitPrice;

    /**
     * 数量
     */
    private Integer amount;

    /**
     * 总价
     */
    private BigDecimal total;

    /**
     * 预计受益人数
     */
    private String benefitNumber;

    /**
     * 状态
     */
    private String state;

    /**
     * 备注
     */
    private String remarks;


    public String getPublishId() {
        return publishId;
    }

    public void setPublishId(String publishId) {
        this.publishId = publishId;
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getBenefitNumber() {
        return benefitNumber;
    }

    public void setBenefitNumber(String benefitNumber) {
        this.benefitNumber = benefitNumber;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public static final String PUBLISH_ID = "publish_id";

    public static final String CATEGORY_ID = "category_id";

    public static final String MATERIAL_NAME = "material_name";

    public static final String MODEL = "model";

    public static final String UNIT = "unit";

    public static final String UNIT_PRICE = "unit_price";

    public static final String AMOUNT = "amount";

    public static final String TOTAL = "total";

    public static final String BENEFIT_NUMBER = "benefit_number";

    public static final String REMARKS = "remarks";

    @Override
    public String toString() {
        return "DisRescueRequest{" +
        "publishId=" + publishId +
        ", categoryId=" + categoryId +
        ", materialName=" + materialName +
        ", model=" + model +
        ", unit=" + unit +
        ", unitPrice=" + unitPrice +
        ", amount=" + amount +
        ", total=" + total +
        ", benefitNumber=" + benefitNumber +
        ", remarks=" + remarks +
        "}";
    }
}

package com.xw.project.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xw.common.entity.BasePlusEntity;

/**
 * <p>
 *
 * </p>
 *
 * @author yuli
 * @since 2020-07-14
 */
@TableName("dis_donation_intention_detail")
public class DisDonationIntentionDetail extends BasePlusEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 意向id
     */
    private String intentionId;


    /**
     * 类别id
     */
    private String categoryIds;
    /**
     * 物资名称
     */
    private String materialName;

    /**
     * 型号
     */
    private String model;

    /**
     * 规格
     */
    private String standard;

    /**
     * 单位
     */
    private String unit;

    private String unitPrice;

    /**
     * 数量
     */
    private String amount;

    /**
     * 总价
     */
    private String total;

    /**
     * 捐赠意向
     */
    private String intention;


    public String getIntentionId() {
        return intentionId;
    }

    public void setIntentionId(String intentionId) {
        this.intentionId = intentionId;
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

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getIntention() {
        return intention;
    }

    public void setIntention(String intention) {
        this.intention = intention;
    }

    public static final String INTENTION_ID = "intention_id";

    public static final String MATERIAL_NAME = "material_name";

    public static final String MODEL = "model";

    public static final String STANDARD = "standard";

    public static final String UNIT = "unit";

    public static final String UNIT_PRICE = "unit_price";

    public static final String AMOUNT = "amount";

    public static final String TOTAL = "total";

    public static final String INTENTION = "intention";

    public String getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(String categoryIds) {
        this.categoryIds = categoryIds;
    }

    @Override
    public String toString() {
        return "OthDonationIntentionDetail{" +
                "intentionId=" + intentionId +
                ", materialName=" + materialName +
                ", model=" + model +
                ", standard=" + standard +
                ", unit=" + unit +
                ", unitPrice=" + unitPrice +
                ", amount=" + amount +
                ", total=" + total +
                ", intention=" + intention +
                "}";
    }
}

package com.xw.project.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xw.common.entity.BasePlusEntity;

/**
 * <p>
 *
 * </p>
 *
 * @author yuli
 * @since 2020-07-01
 */
@TableName("dis_material_donation_require")
public class DisMaterialDonationRequire extends BasePlusEntity {

    private static final long serialVersionUID = 1L;

    private String materialId;

    /**
     * 物资要求
     */
    private String donationRequire;


    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public String getDonationRequire() {
        return donationRequire;
    }

    public void setDonationRequire(String donationRequire) {
        this.donationRequire = donationRequire;
    }

    public static final String MATERIAL_ID = "material_id";

    public static final String DONATION_REQUIRE = "donation_require";

    @Override
    public String toString() {
        return "DisMaterialDonationRequire{" +
                "materialId=" + materialId +
                ", donationRequire=" + donationRequire +
                "}";
    }
}

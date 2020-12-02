package com.xw.project.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author dy
 * @since 2020-03-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OthDonationCert extends Model<OthDonationCert> {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 捐赠日期
     */
    private String donationDate;

    /**
     * 捐赠者
     */
    private String donor;

    /**
     * 捐赠数量
     */
    private BigDecimal donationAmount;

    /**
     * 捐赠项目
     */
    private String donationProject;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 证书编号
     */
    private String certCode;

    /**
     * 申请时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date applyTime;

    /**
     * 数据产生方式，0自动1手动
     */
    private String buildType;

    /**
     * 是否生成标记（0未生成1已生成）
     */
    private String sign;

    /**
     * 捐赠物资金额带单位(仅导出物资的用)
     */
    private String amount;
    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

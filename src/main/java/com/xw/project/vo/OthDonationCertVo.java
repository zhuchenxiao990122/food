package com.xw.project.vo;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author dy
 * @since 2020-02-25
 */
@ApiModel
@Data
public class OthDonationCertVo extends Model<OthDonationCertVo> {

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
    private Date applyTime;

    /**
     * register时间
     */
    private String registerTime;

    private String amount;
}

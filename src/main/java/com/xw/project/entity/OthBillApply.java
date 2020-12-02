package com.xw.project.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
public class OthBillApply extends Model<OthBillApply> {

    private static final long serialVersionUID = 1L;

    /**
     * 申请id
     */
    private String applyId;

    /**
     * 捐赠人类型
     */
    private String donorType;

    /**
     * 捐赠渠道
     */
    private String donationChannel;

    /**
     * 捐赠时间
     */
    private String donationDate;

    /**
     * 捐赠凭据
     */
    private String donationEvidence;

    /**
     * 捐赠票据抬头
     */
    private String donationBillTitle;

    /**
     * 捐赠项目
     */
    private String donationProject;

    /**
     * 捐赠金额（元）
     */
    private BigDecimal donationAmount;

    /**
     * 申请时间
     */
    private Date applyTime;

    /**
     * 登记日期
     */
    private String registerDate;

    /**
     * 手机号
     */
    private String phoneNumber;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 身份证号
     */
    private String idCard;

    private String email;


    @Override
    protected Serializable pkVal() {
        return this.applyId;
    }

}

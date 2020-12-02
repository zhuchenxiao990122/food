package com.xw.project.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@ApiModel
@Data
public class OthBillApplyVo {
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
     * 手机号
     */
    private String phoneNumber;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 登记时间
     */
    private String registerTime;
    /**
     * 身份证号
     */
    private String idCard;
    /**
     * 身份证号
     */
    private String email;
}


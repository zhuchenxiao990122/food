package com.xw.project.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

/**
 * 功能描述：
 *
 * @Author: 喻礼
 * @Date: 2020/3/30 17:45
 */
@Data
public class DonationIntentionVo {
    private String id;

    /**
     * 捐赠者
     */
    private String donor;

    /**
     * 国籍
     */
    private String nation;

    /**
     * 证书类型
     */
    private String certType;

    /**
     * 证书号码
     */
    private String certCode;
    /**
     * 申请状态
     */
    private String applyStatus;

    /**
     * 捐赠类型
     */
    private String donationType;

    private String area;

    private String orient;

    private String total;

    private String linkPerson;

    private String phone;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date donationDate;

    private String intention;

    private String orientName;

    private String donationTypeName;

    private String areaName;

    private String applyStatusName;
}

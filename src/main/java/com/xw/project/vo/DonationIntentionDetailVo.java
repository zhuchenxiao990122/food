package com.xw.project.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 功能描述：
 *
 * @Author: 喻礼
 * @Date: 2020/3/30 17:45
 */
@Data
public class DonationIntentionDetailVo {
    private String id;

    /**
     * 意向id
     */
    private String intentionId;

    /**
     * 物资名称
     */
    private String materialName;

    /**
     * 型号
     */
    private String model;

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
}

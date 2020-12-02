package com.xw.project.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 功能描述：
 *
 * @Author: 喻礼
 * @Date: 2020/3/30 17:45
 */
@Data
public class DisDonatedMaterialDetailVo {

    private String id;
    /**
     * 物资类别
     */
    private String materialCategoryName;
    /**
     * 物资名称
     */
    private String materialName;
    /**
     * 物资来源
     */
    private String contactName;

    /**
     * 型号
     */
    private String model;

    /**
     * 单位
     */
    private String unit;
    /**
     * 单价
     */

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
     * 已入库数量
     */
    private String inAmount;

}

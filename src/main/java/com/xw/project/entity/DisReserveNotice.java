package com.xw.project.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.util.Date;
import java.io.Serializable;

import com.xw.common.entity.BasePlusEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author yuli
 * @since 2020-04-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DisReserveNotice extends BasePlusEntity<DisReserveNotice> {

    private static final long serialVersionUID = 1L;

    private String warehouseId;

    /**
     * 协调id
     */
    private String coordinateId;

    /**
     * 通知单号
     */
    private String noticeCode;

    private BigDecimal total;

    /**
     * 经手人
     */
    private String handler;

    /**
     * 批准人
     */
    private String ratifier;

    /**
     * 核准人
     */
    private String approver;

    /**
     * 状态0未入库1已入库
     */
    private String status;

    private Date publishTime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

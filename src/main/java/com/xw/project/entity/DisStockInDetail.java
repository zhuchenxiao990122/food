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
 * @since 2020-04-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DisStockInDetail extends BasePlusEntity<DisStockInDetail> {

    private static final long serialVersionUID = 1L;


    private String code;

    private String noticeId;

    private String warehouseId;

    /**
     * 物资类别
     */
    private String materialId;
    /**
     * 数量
     */
    private BigDecimal amount;

    /**
     * 总价
     */
    private BigDecimal total;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

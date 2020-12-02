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
 * @author yuli
 * @since 2020-04-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DisReserveNoticeDetail extends Model<DisReserveNoticeDetail> {

    private static final long serialVersionUID = 1L;

    private String id;

    private String noticeId;

    private BigDecimal inAmount;

    private String materialId;

    /**
     * 数量
     */
    private Integer amount;

    /**
     * 总价
     */
    private BigDecimal total;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 最后更新时间
     */
    private Date updateDate;

    private Date createDate;

    private String updateBy;

    private String createBy;

    /**
     * 0正常，1删除
     */
    private String delFlag;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

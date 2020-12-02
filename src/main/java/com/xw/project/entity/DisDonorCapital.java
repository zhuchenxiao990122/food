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
 * @since 2020-04-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DisDonorCapital extends Model<DisDonorCapital> {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 捐赠人名称
     */
    private String name;

    /**
     * 捐款类型（微信、支付宝、银行卡、现金等）
     */
    private String type;

    /**
     * 捐赠金额
     */
    private BigDecimal amount;

    /**
     * 捐赠项目
     */
    private String project;

    private Date donateTime;

    /**
     * 捐款标识（身份证、银行卡号、支付宝账号等）
     */
    private String identification;

    /**
     * 身份证号
     */
    private String idCard;

    private String phone;

    private String email;

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
        return null;
    }

}

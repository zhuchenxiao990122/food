package com.xw.project.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.util.Date;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xw.common.entity.BasePlusEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * <p>
 *
 * </p>
 *
 * @author yuli
 * @since 2020-04-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DisMaterialWarehouse extends BasePlusEntity<DisMaterialWarehouse> {

    private static final long serialVersionUID = 1L;

    /**
     * 组织 Id
     */
    @NotNull(message = "组织 Id 不能为空")
    private String organizeId;

    /**
     * 仓库类型
     */
    @NotNull(message = "仓库 Id 不能为空")
    private String warehouseType;

    /**
     * 仓库编码
     */
    @NotNull(message = "仓库编码不能为空")
    private String code;

    /**
     * 仓库名称
     */
    private String name;

    /**
     * 面积
     */
    private String area;

    /**
     * 仓库地址
     */
    private String address;

    /**
     * 建立时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date establishDate;

    /**
     * 联系人
     */
    private String linkPerson;

    /**
     * 联系方式
     */
    private String phone;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

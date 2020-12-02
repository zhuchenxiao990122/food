package com.xw.project.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import java.io.Serializable;
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
 * @since 2020-04-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DisMaterialCategorys extends Model<DisMaterialCategorys> {

    private static final long serialVersionUID = 1L;

    private String id;

    @NotNull(message = "类别名称不能为空")
    private String name;

    @NotNull(message = "类别编码不能为空")
    private String code;

    private String parentId;

    private String parentIds;

    /**
     * 分类
     */
    private String sort;

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

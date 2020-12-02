package com.xw.project.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 红十字机构信息表 对sys_organize基本组织信息表进行扩展
 * </p>
 *
 * @author dy
 * @since 2020-03-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OrgRedCross extends Model<OrgRedCross> {

    private static final long serialVersionUID = 1L;

    /**
     * 组织标识
     */
    @NotNull
    private String orgId;

    /**
     * 成立时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date orgFoundDate;

    /**
     * 是否建立党组织
     */
    private String partyExist;

    /**
     * 党组织成立日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date partyFoundDate;

    /**
     * 党组织名称
     */
    private String partyName;

    /**
     * 编制人数
     */
    private Integer permanentStaffTotal;

    /**
     * 编制外专职人数
     */
    private Integer nonpermanentStaffTotal;

    /**
     * 领导职数
     */
    private Integer leaderTotal;

    /**
     * 中层职数
     */
    private Integer middleLeaderTotal;

    /**
     * 参照公务员管理人数
     */
    private Integer referServantTotal;

    /**
     * 未参照公务员管理人数
     */
    private Integer nonreferServantTotal;

    /**
     * 理事会成员数
     */
    private Integer councilMemberTotal;

    /**
     * 监事会成立日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date supervisorsFoundDate;

    /**
     * 联系电话
     */
    private String officePhone;

    /**
     * 传真
     */
    private String fax;

    /**
     * 邮政编码
     */
    private String postCode;

    /**
     * 地址
     */
    private String address;

    /**
     * 办公房屋面积(m²)
     */
    private String officeArea;

    /**
     * 办公房屋产权(年)
     */
    private String officePropertyRight;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 机构级别
     */
    private String  institutionLevel;

    @TableField(exist = false)
    private String  institutionLevelName;


    @Override
    protected Serializable pkVal() {
        return this.orgId;
    }

}

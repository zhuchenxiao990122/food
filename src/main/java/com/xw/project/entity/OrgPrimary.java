package com.xw.project.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 基层组织信息表 
 * </p>
 *
 * @author dy
 * @since 2020-03-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OrgPrimary extends Model<OrgPrimary> {

    private static final long serialVersionUID = 1L;

    /**
     * 所属红会组织
     */
    private String orgId;

    /**
     * 所属类别
     */
    private String orgCategory;

    /**
     * 成立时间
     */
    private Date orgFoundDate;

    /**
     * 会长
     */
    private String president;

    /**
     * 副会长
     */
    private String vicePresident;

    /**
     * 秘书长
     */
    private String secretaryGeneral;

    /**
     * 会员数
     */
    private Integer membershipNum;

    /**
     * 地址
     */
    private String address;

    /**
     * 邮政编码
     */
    private String postCode;

    /**
     * 联系人
     */
    private String conactPerson;

    /**
     * 联系电话
     */
    private String officePhone;

    /**
     * 传真号码
     */
    private String fax;

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
     * 删除标识
     */
    private String delFlag;

    private String email;


    @Override
    protected Serializable pkVal() {
        return this.orgId;
    }

}

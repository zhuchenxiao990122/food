package com.xw.project.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * adult_member 成人会员表
 * </p>
 *
 * @author dy
 * @since 2020-03-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OrgMemberAdult extends Model<OrgMemberAdult> {

    private static final long serialVersionUID = 1L;

    /**
     * 会员标识
     */
    private String memberId;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 性别（1男0女）
     */
    private String sex;

    /**
     * 文化程度(数据字典)
     */
    private String educationDegree;

    /**
     * 出生年月
     */
    private Date birthDate;

    /**
     * 民族（数据字典）
     */
    private String nation;

    /**
     * 职业（数据字典）
     */
    private String profession;

    /**
     * 联系电话
     */
    private String contactPhone;

    /**
     * 工作单位
     */
    private String workUnit;

    /**
     * 住址
     */
    private String address;

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


    @Override
    protected Serializable pkVal() {
        return this.memberId;
    }

}

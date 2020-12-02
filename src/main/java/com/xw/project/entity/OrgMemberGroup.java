package com.xw.project.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 团体会员
 * </p>
 *
 * @author dy
 * @since 2020-03-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OrgMemberGroup extends Model<OrgMemberGroup> {

    private static final long serialVersionUID = 1L;

    /**
     * 会员标识
     */
    private String memberId;

    /**
     * 单位性质
     */
    private String unitProperty;

    /**
     * 法定代表
     */
    private String legalRepresentative;

    /**
     * 单位人数
     */
    private Integer unitNumber;

    /**
     * 联系人
     */
    private String conactPerson;

    /**
     * 常驻地区
     */
    private String permanentArea;

    /**
     * 邮政编码
     */
    private String postCode;

    /**
     * 联系电话
     */
    private String conactPhone;

    /**
     * 电子邮箱
     */
    private String mail;

    /**
     * 联系地址
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

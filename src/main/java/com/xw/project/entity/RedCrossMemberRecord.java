package com.xw.project.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xw.common.entity.BasePlusEntity;
import com.xw.core.constant.DocConstant;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 会员信息基本表
 * </p>
 *
 * @author caoshuhao
 * @since 2020-07-07
 */
@Data
@TableName("red_cross_member_record")
public class RedCrossMemberRecord extends BasePlusEntity {

private static final long serialVersionUID=1L;

    /**
     * 所属组织
     */
    private String orgId;

    /**
     * 会员类型（1.成人会员2青少年会员3.团体会员）
     */
    private String memberType;

    /**
     * 会员名称
     */
    private String memberName;

    /**
     * 转入组织
     */
    private String intoOrg;

    /**
     * 会员编号
     */
    private String memberCode;

    /**
     * 申请日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date applyDate;

    /**
     * 审批时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date approveDate;

    /**
     * 申请类型：10转会申请10退会申请10除名申请
     */
    private String applyType;
    /**
     * 审批状态
     */
    private String approvalStatus;

    /**
     * 转会/退会/除名  原因
     */
    private String reason;

    /**
     * 备注
     */
    private String remarks;


    public static final String ORG_ID = "org_id";

    public static final String MEMBER_TYPE = "member_type";

    public static final String MEMBER_NAME = "member_name";

    public static final String INTO_ORG = "into_org";

    public static final String MEMBER_CODE = "member_code";

    public static final String APPLY_DATE = "apply_date";

    public static final String APPROVE_DATE = "approve_date";

    public static final String APPLY_TYPE = "apply_type";

    public static final String REASON = "reason";

    public static final String REMARKS = "remarks";

    @Override
    public String toString() {
        return "RedCrossMemberRecord{" +
        "orgId=" + orgId +
        ", memberType=" + memberType +
        ", memberName=" + memberName +
        ", intoOrg=" + intoOrg +
        ", memberCode=" + memberCode +
        ", applyDate=" + applyDate +
        ", approveDate=" + approveDate +
        ", applyType=" + applyType +
        ", reason=" + reason +
        ", remarks=" + remarks +
        "}";
    }
}

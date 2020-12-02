package com.xw.project.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xw.common.entity.BasePlusEntity;

/**
 * <p>
 * 会员信息基本表
 * </p>
 *
 * @author caoshuhao
 * @since 2020-07-06
 */
@TableName("red_cross_member")
public class RedCrossMember extends BasePlusEntity {

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
     * 会员编号
     */
    private String memberCode;

    /**
     * 申请日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date applyDate;

    /**
     * 入会时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date joinDate;

    /**
     * 申请状态(000申请中001申请通过即正式会员010申请失败）
     */
    private String applyStatus;


    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public String getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(String applyStatus) {
        this.applyStatus = applyStatus;
    }

    public static final String ORG_ID = "org_id";

    public static final String MEMBER_TYPE = "member_type";

    public static final String MEMBER_NAME = "member_name";

    public static final String MEMBER_CODE = "member_code";

    public static final String APPLY_DATE = "apply_date";

    public static final String JOIN_DATE = "join_date";

    public static final String APPLY_STATUS = "apply_status";

    @Override
    public String toString() {
        return "RedCrossMember{" +
        "orgId=" + orgId +
        ", memberType=" + memberType +
        ", memberName=" + memberName +
        ", memberCode=" + memberCode +
        ", applyDate=" + applyDate +
        ", joinDate=" + joinDate +
        ", applyStatus=" + applyStatus +
        "}";
    }
}

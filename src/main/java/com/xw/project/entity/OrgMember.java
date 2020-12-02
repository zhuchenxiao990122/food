package com.xw.project.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xw.common.entity.BasePlusEntity;

import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 成员信息基本表
 * </p>
 *
 * @author caoshuhao
 * @since 2020-06-20
 */
@TableName("org_member")
public class OrgMember extends BasePlusEntity {

private static final long serialVersionUID=1L;

    /**
     * 所属组织
     */
    private String orgId;

    /**
     * 职务
     */
    private String post;

    @NotEmpty
    private String memberName;

    /**
     * 办公电话
     */
    private String officePhone;

    /**
     * 联系电话
     */
    private String mobilPhone;

    /**
     * 邮箱
     */
    private String email;


    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public String getMobilPhone() {
        return mobilPhone;
    }

    public void setMobilPhone(String mobilPhone) {
        this.mobilPhone = mobilPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static final String ORG_ID = "org_id";

    public static final String POST = "post";

    public static final String MEMBER_NAME = "member_name";

    public static final String OFFICE_PHONE = "office_phone";

    public static final String MOBIL_PHONE = "mobil_phone";

    public static final String EMAIL = "email";

    @Override
    public String toString() {
        return "OrgMember{" +
        "orgId=" + orgId +
        ", post=" + post +
        ", memberName=" + memberName +
        ", officePhone=" + officePhone +
        ", mobilPhone=" + mobilPhone +
        ", email=" + email +
        "}";
    }
}

package com.xw.project.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xw.common.entity.BasePlusEntity;

/**
 * <p>
 * 
 * </p>
 *
 * @author yuli
 * @since 2020-06-20
 */
@TableName("com_donors")
public class ComDonors extends BasePlusEntity {

private static final long serialVersionUID=1L;

    /**
     * 单位/组织 、个人
     */
    private String type;

    private String orgId;

    private String name;

    private String loginName;

    private String password;

    /**
     * 国籍
     */
    private String nation;

    /**
     * 身份认证类型
     */
    private String certType;

    /**
     * 证件号码
     */
    private String certCode;

    /**
     * 地址
     */
    private String address;

    /**
     * 备注
     */
    private String remarks;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getCertType() {
        return certType;
    }

    public void setCertType(String certType) {
        this.certType = certType;
    }

    public String getCertCode() {
        return certCode;
    }

    public void setCertCode(String certCode) {
        this.certCode = certCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public static final String TYPE = "type";

    public static final String ORG_ID = "org_id";

    public static final String NAME = "name";

    public static final String LOGIN_NAME = "login_name";

    public static final String PASSWORD = "password";

    public static final String NATION = "nation";

    public static final String CERT_TYPE = "cert_type";

    public static final String CERT_CODE = "cert_code";

    public static final String ADDRESS = "address";

    public static final String REMARKS = "remarks";

    @Override
    public String toString() {
        return "ComDonors{" +
        "type=" + type +
        ", orgId=" + orgId +
        ", name=" + name +
        ", loginName=" + loginName +
        ", password=" + password +
        ", nation=" + nation +
        ", certType=" + certType +
        ", certCode=" + certCode +
        ", address=" + address +
        ", remarks=" + remarks +
        "}";
    }
}

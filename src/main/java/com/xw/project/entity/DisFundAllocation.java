package com.xw.project.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xw.common.entity.BasePlusEntity;

import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author yuli
 * @since 2020-07-23
 */
@TableName("dis_fund_allocation")
public class DisFundAllocation extends BasePlusEntity {

private static final long serialVersionUID=1L;

    private String orgId;

    private String projectId;

    /**
     * 编码
     */
    private String code;

    /**
     * 拨款事由
     */
    private String reason;

    /**
     * 拨款金额
     */
    private BigDecimal amount;

    /**
     * 接收单位
     */
    private String contactItemId;


    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getContactItemId() {
        return contactItemId;
    }

    public void setContactItemId(String contactItemId) {
        this.contactItemId = contactItemId;
    }

    public static final String ORG_ID = "org_id";

    public static final String PROJECT_ID = "project_id";

    public static final String CODE = "code";

    public static final String REASON = "reason";

    public static final String COUNT = "count";

    public static final String CONTACT_ITEM_ID = "contact_item_id";
    
}

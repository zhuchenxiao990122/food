package com.xw.project.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xw.common.entity.BasePlusEntity;
import java.time.LocalDateTime;

/**
 * <p>
 * 红十字机构内部组织表
 * </p>
 *
 * @author caoshuhao
 * @since 2020-06-11
 */
@TableName("org_internal")
public class OrgInternal extends BasePlusEntity {

private static final long serialVersionUID=1L;

    /**
     * 内部组织标识
     */
    private String orgInternalId;

    /**
     * 所属红会组织
     */
    private String orgId;

    /**
     * 组织人员
     */
    private String userId;

    /**
     * 职务
     */
    private String post;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


    public String getOrgInternalId() {
        return orgInternalId;
    }

    public void setOrgInternalId(String orgInternalId) {
        this.orgInternalId = orgInternalId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public static final String ORG_INTERNAL_ID = "org_internal_id";

    public static final String ORG_ID = "org_id";

    public static final String USER_ID = "user_id";

    public static final String POST = "post";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

    @Override
    public String toString() {
        return "OrgInternal{" +
        "orgInternalId=" + orgInternalId +
        ", orgId=" + orgId +
        ", userId=" + userId +
        ", post=" + post +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}

package com.xw.project.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@ApiModel
public class OrgInternalUserVo {
    /**
     * 父组织Id
     */
    @NotEmpty
    private String parentId;
    /**
     * 所属机构Id
     */
    private String orgId;
    /**
     * 组织名称
     */
    @NotEmpty
    private String name;
    /**
     * 组织类型
     */
    @NotEmpty
    private String post;
    /**
     * 组织成员
     */
    private List<OrganizeInternalVo> data;
}

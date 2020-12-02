package com.xw.project.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@ApiModel
@Data
public class OrganizeInternalVo {
    @ApiModelProperty(notes = "组织机构标识")
    private String id;
    @ApiModelProperty(notes = "组织Id")
    private String orgId;
    @ApiModelProperty(notes = "内部组织名称")
    private String orgName;
    @ApiModelProperty(notes = "组织全称")
    private String fullName;
    @ApiModelProperty(notes = "机构成员名称")
    private String memberName;
    @ApiModelProperty(notes = "用户Id")
    private String userId;
    @NotEmpty
    @ApiModelProperty("用户名称")
    private String userName;
    @ApiModelProperty(notes = "职务")
    private String post;
    @ApiModelProperty(notes = "办公电话")
    private String officePhone;
    @ApiModelProperty(notes = "手机号码")
    private String mobilPhone;
    @ApiModelProperty(notes = "邮箱")
    private String email;
    @ApiModelProperty(notes = "职务名称")
    private String postName;
    @ApiModelProperty(notes = "包含的子节点")
    private List<OrganizeInternalVo> children;
    @ApiModelProperty(notes = "是否包含子节点")
    private boolean hasChildren;
    @ApiModelProperty(notes = "子节点数量")
    private Integer childCount;

}

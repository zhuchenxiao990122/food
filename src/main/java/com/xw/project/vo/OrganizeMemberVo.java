package com.xw.project.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApiModel
@Data
public class OrganizeMemberVo {
    @ApiModelProperty(notes = "组织机构标识")
    private String orgId;
    @ApiModelProperty(notes = "职务名称")
    private String post;
    @ApiModelProperty(notes = "职务成员")
    private String  postUserNames;
}

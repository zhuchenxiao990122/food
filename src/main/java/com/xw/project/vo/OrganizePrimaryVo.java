package com.xw.project.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@ApiModel
@Data
public class OrganizePrimaryVo {
    @ApiModelProperty(notes = "组织名称")
    private String fullName;
    @ApiModelProperty(notes = "组织编码")
    private String orgcoding;
    @ApiModelProperty(notes = "组织Id")
    private String orgId;
    @ApiModelProperty(notes = "所属组织组织Id")
    private String parentId;
    @ApiModelProperty(notes = "所属组织")
    private String parentName;
    @ApiModelProperty(notes = "成立时间")
    private Date orgFoundDate;
    @ApiModelProperty(notes = "会长")
    private String president;
    @ApiModelProperty(notes = "副会长")
    private String vicePresident;
    @ApiModelProperty(notes = "秘书长")
    private String secretaryGeneral;
    @ApiModelProperty(notes = "地址")
    private String address;
    @ApiModelProperty(notes = "联系电话")
    private String officePhone;
    @ApiModelProperty(notes = "邮政编码")
    private String postCode;
    @ApiModelProperty(notes = "传真号码")
    private String fax;
    @ApiModelProperty(notes = "会员数")
    private String membershipNum;
    @ApiModelProperty(notes = "所属分类")
    private String orgCategory;
    @ApiModelProperty(notes = "分类名称")
    private String orgCategoryName;
    @ApiModelProperty(notes = "联系人")
    private String conactPerson;
}


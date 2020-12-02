package com.xw.project.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@ApiModel
@Data
public class RedCrossMemberGroupVo {

    @ApiModelProperty(notes = "会员id")
    private String id;
    @ApiModelProperty(notes = "会员状态")
    private String applyStatus;
    @NotEmpty
    @ApiModelProperty(notes = "会员类型")
    private String  memberType;
    @NotEmpty
    @ApiModelProperty(notes = "单位性质")
    private String unitProperty;
    @ApiModelProperty(notes = "法定代表")
    private String legalRepresentative;
    @ApiModelProperty(notes = "单位人数")
    private Integer unitNumber;
    @ApiModelProperty(notes = "邮编")
    private String postCode;
    @ApiModelProperty(notes = "常驻地区")
    private String permanentArea;
    @ApiModelProperty(notes = "联系地址")
    private String address;
    @ApiModelProperty(notes = "联系人")
    private String contactPerson;
    @ApiModelProperty(notes = "手机号码")
    private String contactPhone;
    @ApiModelProperty(notes = "邮箱")
    private String mail;
    @ApiModelProperty(notes = "所属组织id")
    private String orgId;
    @ApiModelProperty(notes = "所属组织名称")
    private String orgName;
    @ApiModelProperty(notes = "会员姓名")
    private String memberName;
    @ApiModelProperty(notes = "会员编号")
    private String memberCode;
    @ApiModelProperty(notes = "申请日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date applyDate;
    @ApiModelProperty(notes = "入会时间")
    private Date joinDate;
    /**
     * 申请类型
     */
    private String applyType;

    private String applyTypeName;
    /**
     * 审核
     */
    private String  approve;
    @ApiModelProperty(notes = "原因")
    private String reason;
    @ApiModelProperty(notes = "转入组织")
    private String intoOrg;
}

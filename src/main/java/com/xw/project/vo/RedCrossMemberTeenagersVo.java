package com.xw.project.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xw.core.constant.DocConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@ApiModel
@Data
public class RedCrossMemberTeenagersVo {

    @ApiModelProperty(notes = "会员id")
    private String id;
    @ApiModelProperty(notes = "会员状态")
    private String applyStatus;
    @NotEmpty
    @ApiModelProperty(notes = "会员类型")
    private String memberType;
    @NotEmpty
    @ApiModelProperty(notes = "学校名称")
    private String schoolName;
    @NotEmpty
    @ApiModelProperty(notes = "系科")
    private String faculty;
    @NotEmpty
    @ApiModelProperty(notes = "班级")
    private String className;
    @ApiModelProperty(notes = "所属组织id")
    private String orgId;
    @ApiModelProperty(notes = "所属组织名称")
    private String orgName;
    @NotEmpty
    @ApiModelProperty(notes = "会员姓名")
    private String memberName;
    @ApiModelProperty(notes = "会员编号")
    private String memberCode;
    @ApiModelProperty(notes = "申请日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date applyDate;
    @ApiModelProperty(notes = "入会时间")
    private Date joinDate;
    @ApiModelProperty(notes = "申请类型")
    private String applyType;
    @ApiModelProperty(notes = "")
    private String applyTypeName;
    @ApiModelProperty(notes = "审核")
    private String  approve;
    @ApiModelProperty(notes = "原因")
    private String reason;
    @ApiModelProperty(notes = "转入组织")
    private String intoOrg;

}

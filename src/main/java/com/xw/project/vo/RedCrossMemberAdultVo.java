package com.xw.project.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@ApiModel
@Data
public class RedCrossMemberAdultVo {

    private String id;
    /**
     * 组织id
     */
    @NotEmpty
    private String orgId;
    private String orgName;
    //转入组织
    private String intoOrg;
    /**
     * 会员姓名
     */
    @NotEmpty
    private String memberName;
    /**
     * 会员类型
     */
    @NotEmpty
    private String memberType;
    /**
     * 会员编号
     */
    private String memberCode;
    /**
     * 性别
     */
    private String sex;
    /**
     * 性别
     */
    private String  gender;
    /**
     * 文化层度
     */
    private String educationDegree;
    /**
     * 生日
     */
    private Date birthDate;
    /**
     * 民族
     */
    private String nation;
    /**
     * 法定代表
     */
    private String legalRepresentative;
    /**
     * 职业
     */
    private String profession;
    /**
     * 身份证
     */
    private String idCard;
    /**
     * 联系方式
     */
    private String contactPhone;
    /**
     * 工作单位
     */
    private String workUnit;
    /**
     * 地址
     */
    private String address;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date applyDate;
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

}



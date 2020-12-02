package com.xw.project.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * @author CAO
 */
@Data
public class RedCrossMemberVo {

    private String id;
    /**
     *组织id
     */
    @NotEmpty
    private String orgId;
    private String orgName;
    /**
     * 会员姓名
     */
    @NotEmpty

    private String memberName;
    /**
     * 会员类型
     */
    @NotEmpty
    @Pattern(regexp = "^[1,2,3]$" )
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

    private String schoolName;
    private String faculty;
    private String className;

    /**
     * 申请时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date applyDate;
    /**
     * 申请类型
     */
    private String applyType;
    private String approvalStatus;
    private String applyTypeName;

    /**
     * 审核
     */
    @Pattern(regexp = "^[0,1]$")
    private String  approve;

    /**
     * 常驻地区
     */
    private String permanentArea;

    /**
     * 邮编
     */
    private String  postCode;
}

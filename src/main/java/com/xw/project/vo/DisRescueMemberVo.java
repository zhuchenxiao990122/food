package com.xw.project.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xw.project.entity.DisRescueMember;
import lombok.Data;

import java.util.Date;

@Data
public class DisRescueMemberVo {
    /**
     * 人员 ID*/
    private String id;

    /**
     * 人员-队伍所属组织 ID
     */
    private String orgId;

    /**
     * 人员-队伍所属组织名称
     */
    private String orgName;

    /**
     * 救援队类型
     */
    private String teamType;

    /**
     * 救援队名称
     */
    private String teamTypeName;

    /**
     * 队伍名称
     */
    private String teamName;

        /**
     * 队伍ID
     */
    private String teamId;

    /**
     * 人员加入时间
     */
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date joinDate;

     /**
     * 队伍人员名称
     */
    private String memberName;

    /**
     * 队伍人员性别
     */
    private String sex;

    /**
     * 出生年月
     */
    private Date birthday;

    /**
     * 身份证
     */
    private String idCard;

    /**
     * 队伍人员职务
     */
    private String duty;

    /**
     * 队伍人员电话
     */
    private String phone;

        /**
     * 队伍人员联系地址
     */
    private String address;

    /**
     * 备注
     */
    private String remarks;
}

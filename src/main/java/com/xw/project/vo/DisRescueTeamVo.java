package com.xw.project.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class DisRescueTeamVo {
    private String id;
    /**
     * 队伍所属组织ID
     */
    private String orgId;

    /**
     * 队伍所属组织名称
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
     * 队伍人数
     */
    private Integer memberTotal;

    /**
     * 队伍创立时间
     */
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date establishDate;

    /**
     * 队伍备注
     */
    private String remarks;

}

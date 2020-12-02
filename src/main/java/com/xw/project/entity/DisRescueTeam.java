package com.xw.project.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xw.common.entity.BasePlusEntity;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author weiLiang
 * @since 2020-06-19
 */
@TableName("dis_rescue_team")
@Data
public class DisRescueTeam extends BasePlusEntity {

    private static final long serialVersionUID=1L;

    /**
     * 队伍所属组织ID
     */
    private String orgId;

    /**
     * 救援队类型
     */
    private String teamType;

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
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date establishDate;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getTeamType() {
        return teamType;
    }

    public void setTeamType(String teamType) {
        this.teamType = teamType;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getMemberTotal() {
        return memberTotal;
    }

    public void setMemberTotal(Integer memberTotal) {
        this.memberTotal = memberTotal;
    }

    public static final String ORG_ID = "org_id";

    public static final String TEAM_TYPE = "team_type";

    public static final String TEAM_NAME = "team_name";

    public static final String MEMBER_TOTAL = "member_total";

    public static final String ESTABLISH_DATE = "establish_date";

    public static final String REMARKS = "remarks";

    @Override
    public String toString() {
        return "DisRescueTeam{" +
        "orgId=" + orgId +
        ", teamType=" + teamType +
        ", teamName=" + teamName +
        ", memberTotal=" + memberTotal +
        ", establishDate=" + establishDate +
        "}";
    }
}

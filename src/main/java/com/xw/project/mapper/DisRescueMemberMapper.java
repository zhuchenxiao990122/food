package com.xw.project.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xw.project.dto.DisRescueMemberEntityDto;
import com.xw.project.entity.DisRescueMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xw.project.entity.DisRescueTeam;
import com.xw.project.vo.DisRescueMemberVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author weiLiang
 * @since 2020-06-18
 */
public interface DisRescueMemberMapper extends BaseMapper<DisRescueMember> {

    String getParentId(@Param("orgId")String orgId);

    IPage<DisRescueMemberVo> listMember(Page<DisRescueMember> page, @Param("parentId")String parentId, @Param("orgId")String orgId, @Param("teamType")String teamType, @Param("teamName")String teamName, @Param("memberName")String memberName, @Param("joinDate")String[] joinDate);

    List<DisRescueTeam> listTeams(@Param("orgId") String orgId, @Param("teamType")String teamType);

    Integer addMember(@Param("id")String id, @Param("memberData")DisRescueMember memberData);

    void addMemberTeam(@Param("uuid")String uuid, @Param("memberId")String memberId, @Param("teamId")String teamId);

    void teamMemberAdd(@Param("teamId")String teamId);

    Integer deleteMember(@Param("id")String id);

    Integer deleteTeamTotal(@Param("teamId")String teamId);

    Integer deleteMemberTeam(@Param("id")String id, @Param("teamId")String teamId);

    Integer updateMember(@Param("memberData")DisRescueMember memberData);
}

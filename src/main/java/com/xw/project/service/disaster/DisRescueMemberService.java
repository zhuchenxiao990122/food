package com.xw.project.service.disaster;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xw.project.dto.DisRescueMemberDto;
import com.xw.project.dto.DisRescueMemberEntityDto;
import com.xw.project.entity.DisRescueMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xw.project.entity.DisRescueTeam;
import com.xw.project.entity.DisRescueTeamType;
import com.xw.project.vo.DisRescueMemberVo;

import java.util.List;

/**
 * <p>
 *  救灾救援-人员管理-服务类
 * </p>
 *
 * @author weiLiang
 * @since 2020-06-18
 */
public interface DisRescueMemberService extends IService<DisRescueMember> {

    IPage<DisRescueMemberVo> listMember(DisRescueMemberDto disRescueMemberDto);

    List<DisRescueTeam>  listTeams(String orgId, String teamType);

    String addMember(DisRescueMemberEntityDto memberData);

    Integer deleteMember(String id, String teamId);

    Integer updateMember(DisRescueMember memberData);
}

package com.xw.project.service.disaster.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xw.common.util.UUIDGenerator;
import com.xw.core.constant.DocConstant;
import com.xw.project.dto.DisRescueMemberDto;
import com.xw.project.dto.DisRescueMemberEntityDto;
import com.xw.project.entity.DisRescueMember;
import com.xw.project.entity.DisRescueTeam;
import com.xw.project.mapper.DisRescueMemberMapper;
import com.xw.project.service.disaster.DisRescueMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xw.project.vo.DisRescueMemberVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 救灾救援-人员管理-服务实现类
 * </p>
 *
 * @author weiLiang
 * @since 2020-06-18
 */
@Service
@Slf4j
public class DisRescueMemberServiceImpl extends ServiceImpl<DisRescueMemberMapper, DisRescueMember> implements DisRescueMemberService {

    @Autowired
    private DisRescueMemberMapper disRescueMemberMapper;

    /**
     * 根据条件查询队伍，如果为空，则查询所有队伍
     */
    @Override
    public List<DisRescueTeam> listTeams(String orgId, String teamType) {
        return disRescueMemberMapper.listTeams(orgId, teamType);
    }


    /**
     * 查询所有人员
     */
    @Override
    public IPage<DisRescueMemberVo> listMember(DisRescueMemberDto disRescueMemberDto) {
        Page<DisRescueMember> page = new Page<>(disRescueMemberDto.getCurrent(), disRescueMemberDto.getSize());
        //通过组织查询人员时，先查看这个人员所在组织，
        //然后查看这个组织的parentID，0 表示省级红十字会，1 表示市级红十字会， 其余为地方红十字
        String parentId = disRescueMemberMapper.getParentId(disRescueMemberDto.getOrgId());
        if (parentId != null) {
            if (parentId.equals("0")) {
                parentId = null;
                disRescueMemberDto.setOrgId(null);
            } else if (parentId.equals("1")) {
                parentId = disRescueMemberDto.getOrgId();
                disRescueMemberDto.setOrgId(null);
            } else {
                parentId = null;
            }
        }
        return disRescueMemberMapper.listMember(page, parentId, disRescueMemberDto.getOrgId(), disRescueMemberDto.getTeamType(), disRescueMemberDto.getTeamName(), disRescueMemberDto.getMemberName(), disRescueMemberDto.getJoinDate());
    }


    /**
     * 新增人员
     */
    @Override
    @Transactional
    public String addMember(DisRescueMemberEntityDto memberData) {

        List<DisRescueMember> members = memberData.getDetailData();
        for (DisRescueMember member : members) {
            String memberId = UUIDGenerator.getUUID();
            try {
                disRescueMemberMapper.addMember(memberId, member);
                //将人员与队伍的关系加入其中
                disRescueMemberMapper.addMemberTeam(UUIDGenerator.getUUID(), memberId, memberData.getTeamId());
                disRescueMemberMapper.teamMemberAdd(memberData.getTeamId());
            } catch (Exception e) {
                return DocConstant.add_error;
            }
        }
        return DocConstant.add_success;
    }


    /**
     * 通过 ID 删除人员
     */
    @Override
    public Integer deleteMember(String id, String teamId) {
        //置人员 del_flag = 1
        Integer i = disRescueMemberMapper.deleteMember(id);
        if (i > 0) {
            //置 另外两张表的关系也为 1
            disRescueMemberMapper.deleteTeamTotal(teamId);
            disRescueMemberMapper.deleteMemberTeam(id, teamId);
        }
        return i;
    }

    /**
     * 更新人员
     */
    @Override
    public Integer updateMember(DisRescueMember memberData) {
        return disRescueMemberMapper.updateMember(memberData);
    }
}

package com.xw.project.service.disaster.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xw.common.result.RestResponse;
import com.xw.common.result.ResultGenerator;
import com.xw.core.constant.DocConstant;
import com.xw.project.dto.DisRescueTeamDto;
import com.xw.project.entity.DisRescueTeam;
import com.xw.project.entity.DisRescueTeamType;
import com.xw.project.mapper.DisRescueTeamMapper;
import com.xw.project.service.disaster.DisRescueTeamService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xw.project.vo.DisRescueTeamVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  救灾救援-队伍管理-服务实现类
 * </p>
 *
 * @author weiLiang
 * @since 2020-06-18
 */
@Service
@Slf4j
public class DisRescueTeamServiceImpl extends ServiceImpl<DisRescueTeamMapper, DisRescueTeam> implements DisRescueTeamService {

    @Autowired
    private DisRescueTeamMapper disRescueTeamMapper;

    /**
     * 查询所有队伍类型
     * */
    @Override
    public List<DisRescueTeamType> listTeamType(){
        return disRescueTeamMapper.listTeamType();
    }


    /**
     * 查询所有队伍
     * */
    @Override
    public IPage<DisRescueTeamVo> listTeam(DisRescueTeamDto disRescueTeamDto){
        Page<DisRescueTeam> page = new Page<>(disRescueTeamDto.getCurrent(),disRescueTeamDto.getSize());
            // 通过组织查询队伍时，先查看这个队伍的parentID，0 表示省级红十字会，1 表示市级红十字会， 其余为地方红十字
            String parentId = disRescueTeamMapper.getParentId(disRescueTeamDto.getOrgId());
            final String PROVINCE_RED_CROSS = "0";
            final String CITY_RED_CROSS = "1";
            if(parentId != null){
                //父ID为 0，查找全部
                if(parentId.equals(PROVINCE_RED_CROSS)){
                    parentId = null;
                    disRescueTeamDto.setOrgId(null);
                    //父ID为 1，则表示不是某个组织了，而是某个市了
                }else if(parentId.equals(CITY_RED_CROSS)){
                    parentId = disRescueTeamDto.getOrgId();
                    disRescueTeamDto.setOrgId(null);
                }else{
                    parentId = null;
                }
            }
        IPage<DisRescueTeamVo> listRestResponse = disRescueTeamMapper.listTeam(page, parentId, disRescueTeamDto.getOrgId(), disRescueTeamDto.getTeamType(), disRescueTeamDto.getTeamName(), disRescueTeamDto.getEstablishDate());
        return listRestResponse;
    }


    /**
     * 新增队伍
     * @param teamData 前端传入的表单数据，包括 所属组织，队伍类型，创建时间，队伍名称，备注
     * */
    @Override
    public String addTeam(DisRescueTeam teamData){
        //调用系统已经生成的默认方法
        int i = disRescueTeamMapper.insert(teamData);
        return i > 0 ? DocConstant.add_success : DocConstant.add_error;
    }


    /**
     * 删除队伍-注意：同时还需删除这个队伍中的所以成员
     * @param id 前端传入的队伍 ID
     * */
    @Override
    public String deleteTeam(String id){
        int i =  disRescueTeamMapper.deleteTeamById(id);
        return i > 0 ? DocConstant.delete_success : DocConstant.delete_error;
    }

    /**
     * 更新队伍
     * @param teamData 前端传入的表单数据，包括 所属组织，队伍类型，创建时间，队伍名称，备注
     * */
    @Override
    public String updateTeam(DisRescueTeam teamData){
        //调用系统已经生成的默认方法
        int i = disRescueTeamMapper.updateById(teamData);
        return i > 0 ? DocConstant.add_success : DocConstant.add_error;
    }
}

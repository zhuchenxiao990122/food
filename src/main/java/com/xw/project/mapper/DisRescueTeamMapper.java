package com.xw.project.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xw.common.result.RestResponse;
import com.xw.project.entity.DisRescueTeam;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xw.project.entity.DisRescueTeamType;
import com.xw.project.vo.DisRescueTeamVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  救灾救援-队伍管理-Mapper 接口
 * </p>
 *
 * @author weiLiang
 * @since 2020-06-18
 */
@Component
public interface DisRescueTeamMapper extends BaseMapper<DisRescueTeam> {

    List<DisRescueTeamType> listTeamType();

    IPage<DisRescueTeamVo> listTeam(Page<DisRescueTeam> page, @Param("parentId") String parentId, @Param("orgId") String orgId, @Param("teamType") String teamType, @Param("teamName")String teamName, @Param("establishDate")String[] establishDate);

    Integer deleteTeamById(String id);

    String getParentId(String orgId);
}

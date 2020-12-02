package com.xw.project.service.disaster;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xw.common.result.RestResponse;
import com.xw.project.dto.DisRescueTeamDto;
import com.xw.project.entity.DisRescueTeam;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xw.project.entity.DisRescueTeamType;
import com.xw.project.vo.DisRescueTeamVo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  救灾救援-队伍管理-服务类
 * </p>
 *
 * @author weiLiang
 * @since 2020-06-18
 */
public interface DisRescueTeamService extends IService<DisRescueTeam> {

    List<DisRescueTeamType> listTeamType();

    IPage<DisRescueTeamVo> listTeam(DisRescueTeamDto disRescueTeamDto);

    String addTeam(DisRescueTeam teamData);

    String deleteTeam(String id);

    String updateTeam(DisRescueTeam teamData);
}

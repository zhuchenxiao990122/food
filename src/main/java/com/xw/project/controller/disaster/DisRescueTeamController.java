package com.xw.project.controller.disaster;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xw.common.result.RestResponse;
import com.xw.common.result.ResultGenerator;
import com.xw.project.dto.DisRescueTeamDto;
import com.xw.project.entity.DisRescueTeam;
import com.xw.project.entity.DisRescueTeamType;
import com.xw.project.entity.TreeNode;
import com.xw.project.service.disaster.DisRescueTeamService;
import com.xw.project.vo.DisRescueTeamVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.xw.common.controller.BaseController;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  救灾救援-队伍管理-前端控制器
 * </p>
 *
 * @author weiLiang
 * @since 2020-06-18
 */
@RestController
@RequestMapping("/disRescueTeam")
@Api(
    value = "DisRescueTeamController",
    description = "救灾救援-队伍管理"
)
public class DisRescueTeamController extends BaseController {
    @Autowired
    private DisRescueTeamService disRescueTeamService;

    @GetMapping(value={"/listTeamType"})
    @ApiOperation(
            value = "获取队伍类型列表",
            notes = "获取所有队伍类型"
    )
    public RestResponse<List<DisRescueTeamType>> listTeamType() {
        return ResultGenerator.genSuccessResult(disRescueTeamService.listTeamType());
    }


    @GetMapping(value={"/listTeam"})
    @ApiOperation(
            value = "获取队伍列表",
            notes = "队伍查询，参数为空时，搜索出所有队伍"
    )
    public RestResponse<IPage<DisRescueTeamVo>> listTeam(DisRescueTeamDto disRescueTeamDto) {
        return ResultGenerator.genSuccessResult(disRescueTeamService.listTeam(disRescueTeamDto));
    }


    /**
     * 新增队伍
     * teamData:前端传入的表单，包括 所属组织，队伍类型，创建时间，队伍名称，备注
     * */
    @PostMapping(value={"/addTeam"})
    @ApiOperation(
            value = "新增队伍",
            notes = "新增队伍"
    )
    public RestResponse<String> addTeam(@Valid @RequestBody DisRescueTeam teamData){
        return ResultGenerator.genSuccessResult(disRescueTeamService.addTeam(teamData));
    }


    @DeleteMapping(value={"/{id}"})
    @ApiOperation(
            value = "删除队伍",
            notes = "前端传入的队伍 ID，如果队伍中有成员，则不会删除"
    )
    public RestResponse<String> deleteTeam(@PathVariable String id){
        return ResultGenerator.genSuccessResult(disRescueTeamService.deleteTeam(id));
    }


    @PutMapping(value={"/updateTeam"})
    @ApiOperation(
            value = "更新队伍",
            notes = "更新队伍"
    )
    public RestResponse<String> updateTeam(@Valid @RequestBody DisRescueTeam teamData){
        return ResultGenerator.genSuccessResult(disRescueTeamService.updateTeam(teamData));
    }
}


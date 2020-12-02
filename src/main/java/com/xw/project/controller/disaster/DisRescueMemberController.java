package com.xw.project.controller.disaster;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xw.common.result.RestResponse;
import com.xw.common.result.ResultGenerator;
import com.xw.project.dto.DisRescueMemberDto;
import com.xw.project.dto.DisRescueMemberEntityDto;
import com.xw.project.entity.DisRescueMember;
import com.xw.project.entity.DisRescueTeam;
import com.xw.project.entity.DisRescueTeamType;
import com.xw.project.service.disaster.DisRescueMemberService;
import com.xw.project.vo.DisRescueMemberVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.xw.common.controller.BaseController;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 *  救灾救援-人员管理-前端控制器
 * </p>
 *
 * @author weiLiang
 * @since 2020-06-18
 */
@RestController
@RequestMapping("/disRescueMember")
@Api(
	value = "DisRescueMemberController",
	description = "救灾救援-人员管理"
)
public class DisRescueMemberController extends BaseController {
    @Autowired
    private DisRescueMemberService disRescueMemberService;

    @GetMapping(value={"/listMember"})
    @ApiOperation(
            value = "获取人员列表",
            notes = "人员查询，参数为空时，搜索出所有人员"
    )
    public RestResponse<IPage<DisRescueMemberVo>> listTeam(DisRescueMemberDto disRescueMemberDto) {
        return ResultGenerator.genSuccessResult(disRescueMemberService.listMember(disRescueMemberDto));
    }

    /**
     * 根据条件查询队伍，如果为空，则查询所有队伍
     * */
    @GetMapping(value={"/listTeams"})
    @ApiOperation(
            value = "根据条件获取队伍",
            notes = "根据条件查询队伍，如果为空，则查询所有队伍"
    )
    public RestResponse<List<DisRescueTeam>> listTeams(String orgId, String teamType) {
        return ResultGenerator.genSuccessResult(disRescueMemberService.listTeams(orgId,teamType));
    }

    @PostMapping(value={"/addMember"})
    @ApiOperation(
            value = "新增人员",
            notes = "新增人员"
    )
    public RestResponse<String> addMember(@Valid @RequestBody DisRescueMemberEntityDto memberData){
        return ResultGenerator.genSuccessResult(disRescueMemberService.addMember(memberData));
    }

    @DeleteMapping(value={"/deleteMember"})
    @ApiOperation(
            value = "删除人员",
            notes = "删除人员"
    )
    public RestResponse<Integer> deleteMember(String id, String teamId){
        return ResultGenerator.genSuccessResult(disRescueMemberService.deleteMember(id,teamId));
    }

    @PutMapping(value={"/updateMember"})
    @ApiOperation(
            value = "更新人员",
            notes = "更新人员"
    )
    public RestResponse<Integer> updateMember(@Valid @RequestBody DisRescueMember memberData){
        return ResultGenerator.genSuccessResult(disRescueMemberService.updateMember(memberData));
    }

}


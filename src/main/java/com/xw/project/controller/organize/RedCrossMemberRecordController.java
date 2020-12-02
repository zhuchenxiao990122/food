package com.xw.project.controller.organize;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xw.common.result.RestResponse;
import com.xw.common.result.ResultGenerator;
import com.xw.common.util.StringUtil;
import com.xw.core.constant.DocConstant;
import com.xw.project.dto.ApproveDto;
import com.xw.project.dto.RedCrossMemberDto;
import com.xw.project.service.organize.RedCrossMemberRecordService;
import com.xw.project.vo.RedCrossMemberVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.xw.common.controller.BaseController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * <p>
 * 会员申请记录表
 * </p>
 *
 * @author caoshuhao
 * @since 2020-07-07
 */
@RestController
@RequestMapping("/redCrossMemberRecord")
@Api(
        value = "RedCrossMemberRecordController",
        description = "会员申请审批"
)
public class RedCrossMemberRecordController extends BaseController {

    @Autowired
    private RedCrossMemberRecordService memberRecordService;

    @GetMapping("/listApproveMember")
    @ApiOperation("会员信息分页查询")
    public RestResponse<IPage<RedCrossMemberVo>> listApproveMember( @Valid RedCrossMemberDto memberDto){
        return memberRecordService.listApproveMember(memberDto);
    }


    @GetMapping("/getMemberById")
    @ApiOperation("会员信息查询")
    public RestResponse<Object> getMemberById( String id,  String memberType){
        if(StringUtil.isNotEmpty(id) && StringUtil.isNotEmpty(memberType)){
            return memberRecordService.getMemberById(id,memberType);
        }
        return ResultGenerator.genSuccessResult(DocConstant.search_error);
    }

    @PostMapping("/approveRegisterMember")
    @ApiOperation("会员入会审批")
    public RestResponse<String> approveRegisterMember(@Valid @RequestBody RedCrossMemberVo redCrossMemberVo){
           return memberRecordService.approveRegisterMember(redCrossMemberVo);
    }

    @PostMapping("/approveWithdrawMember")
    @ApiOperation("会员退会审批")
    public RestResponse<String> approveWithdrawMember(@Valid @RequestBody ApproveDto approveDto){
        return memberRecordService.approveWithdrawMember(approveDto);
    }

    @PostMapping("/approveExpelMember")
    @ApiOperation("会员除名审批")
    public RestResponse<String> approveExpelMember(@Valid @RequestBody ApproveDto approveDto) {
        return memberRecordService.approveExpelMember(approveDto);
    }

    @GetMapping("/memberPendingCount")
    @ApiOperation("未审批会员数量")
    public RestResponse<Integer> memberPendingCount(@Valid RedCrossMemberDto memberDto){
        return memberRecordService.memberPendingCount(memberDto);
    }

    @PostMapping("/approveTransferMember")
    @ApiOperation("会员转会审批")
    public RestResponse<String> approveTransferMember(@Valid @RequestBody ApproveDto approveDto){
        return memberRecordService.approveTransferMember(approveDto);
    }

}


package com.xw.project.controller.organize;


import com.xw.common.result.RestResponse;
import com.xw.common.result.ResultGenerator;
import com.xw.common.util.StringUtil;
import com.xw.core.constant.DocConstant;
import com.xw.project.entity.RedCrossMemberRecord;
import com.xw.project.service.organize.RedCrossMemberService;
import com.xw.project.vo.RedCrossMemberGroupVo;
import com.xw.project.vo.RedCrossMemberVo;
import com.xw.project.vo.RedCrossMemberTeenagersVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.xw.common.controller.BaseController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * <p>
 * 会员基本信息
 * </p>
 *
 * @author caoshuhao
 * @since 2020-07-06
 */
@RestController
@RequestMapping("/redCrossMember")
@Api(
        value = "RedCrossMemberController",
        tags = {"RedCrossMemberController"},
        description = "会员基本信息"
)
public class RedCrossMemberController extends BaseController {

    @Autowired
    private RedCrossMemberService redCrossMemberService;

    @PostMapping("/addMemberAdult")
    @ApiOperation(value = "成人会员入会申请")
    public RestResponse<String> addMemberAdult(@Valid @RequestBody RedCrossMemberVo redCrossMemberVo){
        return redCrossMemberService.addMemberAdult(redCrossMemberVo);
    }

    @PostMapping("/addTeenagers")
    @ApiOperation(value = "青少年会员入会申请")
    public  RestResponse<String > addTeenagers(@Valid @RequestBody RedCrossMemberTeenagersVo redCrossMemberYoungVo){
        return redCrossMemberService.addTeenagers(redCrossMemberYoungVo);
    }

    @PostMapping("/addGroup")
    @ApiOperation(value = "团体会员入会申请")
    public RestResponse<String> addGroup(@Valid @RequestBody RedCrossMemberGroupVo redCrossMemberGroupVo){
        return redCrossMemberService.addGroup(redCrossMemberGroupVo);
    }

    @PutMapping("/memberTransfer")
    @ApiOperation(value = "会员转会申请")
    public RestResponse<String> memberTransfer(@Valid @RequestBody RedCrossMemberRecord redCrossMemberRecord){
        if(StringUtil.isNotEmpty(redCrossMemberRecord.getIntoOrg())){
            return  redCrossMemberService.memberTransfer(redCrossMemberRecord);
        }
        return ResultGenerator.genFailResult(DocConstant.application_submissions_error);
    }

    @PutMapping("/memberWithdraw")
    @ApiOperation(value = "会员退会申请")
    public RestResponse<String> memberWithdraw(@Valid @RequestBody RedCrossMemberRecord redCrossMemberRecord){
        return redCrossMemberService.memberWithdraw(redCrossMemberRecord);
    }

    @PutMapping("/memberExpel")
    @ApiOperation(value = "会员除名申请")
    public RestResponse<String> memberExpel(@Valid @RequestBody RedCrossMemberRecord redCrossMemberRecord){
        return redCrossMemberService.memberExpel(redCrossMemberRecord);
    }


}


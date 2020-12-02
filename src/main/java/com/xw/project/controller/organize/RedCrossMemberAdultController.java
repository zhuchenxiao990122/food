package com.xw.project.controller.organize;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xw.common.result.RestResponse;
import com.xw.common.result.ResultGenerator;
import com.xw.common.util.StringUtil;
import com.xw.core.constant.DocConstant;
import com.xw.project.dto.RedCrossMemberDto;
import com.xw.project.entity.RedCrossMemberAdult;
import com.xw.project.entity.RedCrossMemberRecord;
import com.xw.project.service.organize.RedCrossMemberAdultService;
import com.xw.project.service.organize.RedCrossMemberService;
import com.xw.project.vo.RedCrossMemberAdultVo;
import com.xw.project.vo.RedCrossMemberVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.bouncycastle.crypto.prng.RandomGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.xw.common.controller.BaseController;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 成人会员表
 * </p>
 *
 * @author caoshuhao
 * @since 2020-07-06
 */
@RestController
@RequestMapping("/redCrossMemberAdult")
@Api(
        value = "redCrossMemberAdultController",
        description = "成人会员"
)
public class RedCrossMemberAdultController extends BaseController {

    @Autowired
    private RedCrossMemberAdultService memberAdultService;

    @GetMapping("/listRedCrossMemberAdult")
    @ApiOperation("成人会员分页查询")
    public RestResponse<IPage<RedCrossMemberAdultVo>> listRedCrossMemberAdult(RedCrossMemberDto redCrossMemberDto){
        return memberAdultService.listRedCrossMemberAdult(redCrossMemberDto);
    }

    @GetMapping("/getCrossMemberAdult")
    @ApiOperation("查询成人会员信息")
    public RestResponse<RedCrossMemberVo> getCrossMemberAdult(String  memberCode ){
        if(StringUtil.isNotEmpty(memberCode)){
            return memberAdultService.getCrossMemberAdult(memberCode);
        }else {
            return ResultGenerator.genFailResult(DocConstant.search_error);
        }
    }
}


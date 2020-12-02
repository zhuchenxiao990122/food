package com.xw.project.controller.organize;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xw.common.result.RestResponse;
import com.xw.common.result.ResultGenerator;
import com.xw.common.util.StringUtil;
import com.xw.core.constant.DocConstant;
import com.xw.project.dto.RedCrossMemberDto;
import com.xw.project.service.organize.RedCrossMemberAdultService;
import com.xw.project.service.organize.RedCrossMemberGroupService;
import com.xw.project.vo.RedCrossMemberAdultVo;
import com.xw.project.vo.RedCrossMemberGroupVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.xw.common.controller.BaseController;

/**
 * <p>
 * 团体会员
 * </p>
 *
 * @author caoshuhao
 * @since 2020-07-06
 */
@RestController
@RequestMapping("/redCrossMemberGroup")
@Api(
        value = "RedCrossMemberGroupController",
        description = "团体会员"

)
public class RedCrossMemberGroupController extends BaseController {
    @Autowired
    private RedCrossMemberGroupService memberGroupService;

    @GetMapping("/listRedCrossMemberGroup")
    @ApiOperation("团体会员分页查询")
    public RestResponse<IPage<RedCrossMemberAdultVo>> listRedCrossMemberGroup(RedCrossMemberDto redCrossMemberDto) {
        return memberGroupService.listRedCrossMemberGroup(redCrossMemberDto);
    }

    @GetMapping("/getCrossMemberGroup")
    @ApiOperation("团体会员详细信息")
    public RestResponse<RedCrossMemberGroupVo> getCrossMemberGroup(String memberCode) {
        if (StringUtil.isNotEmpty(memberCode)) {
            return memberGroupService.getCrossMemberGroup(memberCode);
        }
        return ResultGenerator.genFailResult(DocConstant.search_error);
    }

}


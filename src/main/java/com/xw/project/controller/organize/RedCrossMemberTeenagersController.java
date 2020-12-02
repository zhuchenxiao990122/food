package com.xw.project.controller.organize;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xw.common.result.RestResponse;
import com.xw.project.dto.RedCrossMemberDto;
import com.xw.project.service.organize.RedCrossMemberTeenagersService;
import com.xw.project.vo.RedCrossMemberTeenagersVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.xw.common.controller.BaseController;

/**
 * <p>
 * 青少年会员
 * </p>
 *
 * @author caoshuhao
 * @since 2020-07-06
 */
@RestController
@RequestMapping("/redCrossMemberTeenagers")
@Api(
        value = "redCrossMemberYoungController",
        description = "青少年会员"
)
public class RedCrossMemberTeenagersController extends BaseController {

    @Autowired
    private RedCrossMemberTeenagersService memberYoungService;

    @GetMapping("/listMemberTeenagers")
    @ApiOperation(value = "青少年会员分页查询")
    public RestResponse<IPage<RedCrossMemberTeenagersVo>> listRedCrossMemberYoung(RedCrossMemberDto redCrossMemberDto){
        return memberYoungService.listRedCrossMemberYoung(redCrossMemberDto);
    }

    @GetMapping("/getCrossMemberTeenagers")
    @ApiOperation(value = "获取青少年信息")
    public RestResponse<RedCrossMemberTeenagersVo> getCrossMemberTeenagers(String memberCode){
        return memberYoungService.getCrossMemberTeenagers(memberCode);
    }


}


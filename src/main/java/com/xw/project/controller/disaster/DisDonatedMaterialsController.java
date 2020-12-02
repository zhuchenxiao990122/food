package com.xw.project.controller.disaster;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xw.common.result.RestResponse;
import com.xw.common.result.ResultGenerator;
import com.xw.project.service.disaster.DisDonatedMaterialsDetailService;
import com.xw.project.service.disaster.DisDonatedMaterialsService;
import com.xw.project.vo.DisDonatedMaterialsVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yuli
 * @since 2020-04-08
 */
@RestController
@Api(value = "捐赠物资")
@RequestMapping("/disDonatedMaterials")
public class DisDonatedMaterialsController {
    @Autowired
    private DisDonatedMaterialsService disDonatedMaterialsService;

    @RequestMapping(value = {"/intentionList"})
    @ApiOperation(value = "获得所有有意向捐赠人")
    public RestResponse<List<Map<String, Object>>> intentionList() {
        return ResultGenerator.genSuccessResult(disDonatedMaterialsService.intentionList());
    }

    @PostMapping(value = {"/save"})
    @ApiOperation(value = "保存捐赠物资信息")
    public RestResponse<String> save(@RequestBody Map<String, Object> map) {
        int result = disDonatedMaterialsService.save(map);
        if (result > 0) {
            return ResultGenerator.genSuccessResult("新增成功");
        } else {
            return ResultGenerator.genFailResult("新增失败");
        }
    }

    @GetMapping(value = {"/list"})
    @ApiOperation(value = "保存捐赠物资信息")
    public RestResponse<IPage<DisDonatedMaterialsVo>> pagesList(Integer current, Integer size) {
        return ResultGenerator.genSuccessResult(disDonatedMaterialsService.pagesList(current, size));
    }
}

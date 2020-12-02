package com.xw.project.controller.disaster;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xw.common.result.RestResponse;
import com.xw.common.result.ResultGenerator;
import com.xw.common.util.StringUtil;
import com.xw.core.constant.DocConstant;
import com.xw.project.dto.DisPublishDto;
import com.xw.project.entity.DisDisasterPublish;
import com.xw.project.service.disaster.DisDisasterPublishService;
import com.xw.project.vo.DisPublishVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.xw.common.controller.BaseController;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 灾情发布
 * </p>
 *
 * @author caoshuhao
 * @since 2020-06-23
 */
@Slf4j
@RestController
@RequestMapping("/disPublish")
@Api(
        value = "DisDisasterPublishController",
        description = "灾情发布",
        tags = {"DisDisasterPublishController"})

public class DisDisasterPublishController extends BaseController {

    @Autowired
    private DisDisasterPublishService disDisasterPublishService;

    @GetMapping("/search")
    @ApiOperation("查询")
    public RestResponse<IPage<DisPublishVo>> search(DisPublishDto disPublishDto) {
        return disDisasterPublishService.search(disPublishDto);
    }

    @PostMapping(value = {"/addDisasterPublish"})
    @ApiOperation("新增")
    public RestResponse<String> addDisasterPublish(@Valid @RequestBody DisPublishVo disPublishVo)  {
       return disDisasterPublishService.addDisasterPublish(disPublishVo);
    }

    @GetMapping("/getDisPublish")
    @ApiOperation("根据id查询灾情详情")
    public RestResponse<DisPublishVo> getDisPublish(String id) {
        if(StringUtil.isNotEmpty(id)){
            return disDisasterPublishService.getDisPublish(id);
        }else {
            return ResultGenerator.genFailResult(DocConstant.search_error);
        }
    }

    @PutMapping("/updateDisPublish")
    @ApiOperation("修改灾情信息")
    public RestResponse<String> updateDisPublish(@Valid @RequestBody DisPublishVo disPublishVo) {
            return disDisasterPublishService.updateDisPublish(disPublishVo);
    }

    @DeleteMapping("/deleteDisPublish")
    @ApiOperation("删除灾害信息")
    public RestResponse<String> deleteDisPublish(String id) {
        if(StringUtil.isNotEmpty(id)){
            return disDisasterPublishService.deleteDisPublish(id);
        }else {
            return ResultGenerator.genFailResult(DocConstant.delete_error);
        }
    }

}


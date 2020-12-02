package com.xw.project.controller.advertise;

import com.xw.core.constant.DocConstant;
import com.xw.project.entity.TargetPosition;
import com.xw.project.service.common.TargetPositionService;
import com.xw.project.vo.AdvRcParkVo;
import com.xw.project.vo.RtmAedVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.xw.project.dto.AdvRcParkDto;
import lombok.extern.slf4j.Slf4j;
import com.xw.common.controller.BaseController;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xw.common.result.RestResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.xw.common.result.ResultGenerator;
import com.xw.project.service.advertise.AdvRcParkService;
import com.xw.project.entity.AdvRcPark;

import java.util.List;

/**
 * @author Jy
 * @since 2020-07-03
 */
@Api(description = "获取红十字会文化主题公园详情")
@RestController
@RequestMapping("/advRcPark")
@Slf4j
public class AdvRcParkController extends BaseController {

    @Autowired
    private AdvRcParkService advRcParkService;

    @Autowired
    private TargetPositionService targetPositionService;

    @ApiOperation("分页")
    @GetMapping("/page")
    public RestResponse<IPage<AdvRcParkVo>> page(AdvRcParkDto advRcParkDto) {
        try {
            return ResultGenerator.genSuccessResult(advRcParkService.page(advRcParkDto));
        } catch (Exception e) {
            log.error(DocConstant.search_error, e);
            return ResultGenerator.genFailResult(DocConstant.search_error);
        }
    }


    @ApiOperation("添加")
    @PostMapping("/add")
    public RestResponse<String> add(@RequestBody AdvRcParkDto advRcParkDto) {
        if (advRcParkDto.getId() != null) {
            advRcParkDto.setId(null);
        }
        return  advRcParkService.add(advRcParkDto);
    }

    @ApiOperation("修改")
    @PutMapping("/update")
    public RestResponse<String> update(@RequestBody AdvRcParkDto advRcParkDto) {
        return  advRcParkService.update(advRcParkDto);
    }

    @ApiOperation("删除")
    @DeleteMapping("/delete/{id}")
    public RestResponse<String> delete(@PathVariable String id) {
        return advRcParkService.delete(id);
    }

    @GetMapping(
            value = {"/{id}"},
            produces = {"application/json; charset=utf-8"}
    )
    @ApiOperation(
            value = "获取红十字会文化主题公园详情",
            notes = "获取红十字会文化主题公园详情",
            code = 200,
            produces = "application/json"
    )
    public RestResponse<AdvRcParkVo> getByIdVo(@PathVariable String id) {
        return ResultGenerator.genSuccessResult(this.advRcParkService.getByIdVo(id));
    }

    @GetMapping(value = "/listTreeArea")
    @ApiOperation(value = "查询省市区")
    public RestResponse<List<AdvRcParkVo>> listTreeArea() {
        return ResultGenerator.genSuccessResult(advRcParkService.listTreeArea());
    }

    @GetMapping(value = {"/list"}, produces = {"application/json; charset=utf-8"})
    @ApiOperation(value = "获取获取红十字会文化主题公园所有地点信息")
    public RestResponse listAllAddress() {
        return ResultGenerator.genSuccessResult(advRcParkService.listAllAddress());
    }

}
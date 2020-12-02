package com.xw.project.controller.advertise;

import com.xw.core.constant.DocConstant;
import com.xw.project.entity.TargetPosition;
import com.xw.project.service.common.TargetPositionService;
import com.xw.project.vo.AdvPrayLifePlaceVo;
import com.xw.project.vo.AdvPrayLifePlaceVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.xw.project.dto.AdvPrayLifePlaceDto;
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
import com.xw.project.service.advertise.AdvPrayLifePlaceService;
import com.xw.project.entity.AdvPrayLifePlace;

import java.util.List;

/**
 * @author Jy
 * @since 2020-07-06
 */
@Api(description = "生命礼赞场所管理")
@RestController
@RequestMapping("/advPrayLifePlace")
@Slf4j
public class AdvPrayLifePlaceController extends BaseController {

    @Autowired
    private AdvPrayLifePlaceService advPrayLifePlaceService;

    @Autowired
    private TargetPositionService targetPositionService;

    @ApiOperation("分页")
    @GetMapping("/page")
    public RestResponse<IPage<AdvPrayLifePlaceVo>> page(AdvPrayLifePlaceDto advPrayLifePlaceDto) {
        try {
            return ResultGenerator.genSuccessResult(advPrayLifePlaceService.page(advPrayLifePlaceDto));
        } catch (Exception e) {
            log.error(DocConstant.search_error, e);
            return ResultGenerator.genFailResult(DocConstant.search_error);
        }
    }


    @ApiOperation("添加")
    @PostMapping("/add")
    public RestResponse<String> add(@RequestBody AdvPrayLifePlaceDto advPrayLifePlaceDto) {
        //防止新增传过来ID不为空
        if (advPrayLifePlaceDto.getId() != null) {
            advPrayLifePlaceDto.setId(null);
        }
        return advPrayLifePlaceService.add(advPrayLifePlaceDto);
    }

    @ApiOperation("修改")
    @PutMapping("/update")
    public RestResponse<String> update(@RequestBody AdvPrayLifePlaceDto advPrayLifePlaceDto) {
        return advPrayLifePlaceService.update(advPrayLifePlaceDto);
    }

    @ApiOperation("删除")
    @DeleteMapping("/delete/{id}")
    public RestResponse<String> delete(@PathVariable String id) {
        return advPrayLifePlaceService.delete(id);
    }

    @GetMapping(
            value = {"/{id}"},
            produces = {"application/json; charset=utf-8"}
    )
    @ApiOperation(
            value = "获取礼赞场所详情",
            notes = "获取礼赞场所详情",
            code = 200,
            produces = "application/json"
    )
    public RestResponse<AdvPrayLifePlaceVo> getByIdVo(@PathVariable String id) {
        return ResultGenerator.genSuccessResult(this.advPrayLifePlaceService.getByIdVo(id));
    }

    @GetMapping(value = "/listTreeArea")
    @ApiOperation(value = "查询省市区")
    public RestResponse<List<AdvPrayLifePlaceVo>> listTreeArea() {
        return ResultGenerator.genSuccessResult(advPrayLifePlaceService.listTreeArea());
    }

    @GetMapping(value = {"/list"}, produces = {"application/json; charset=utf-8"})
    @ApiOperation(value = "获取体验馆所有地点")
    public RestResponse listAllAddress() {
        return ResultGenerator.genSuccessResult(advPrayLifePlaceService.listAllAddress());
    }

}
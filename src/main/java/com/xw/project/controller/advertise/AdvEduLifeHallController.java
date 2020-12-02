package com.xw.project.controller.advertise;


import com.xw.core.constant.DocConstant;
import com.xw.project.entity.TargetPosition;
import com.xw.project.service.common.TargetPositionService;
import com.xw.project.vo.AdvEduLifeHallVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.xw.project.dto.AdvEduLifeHallDto;
import lombok.extern.slf4j.Slf4j;
import com.xw.common.controller.BaseController;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xw.common.result.RestResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.xw.common.result.ResultGenerator;
import com.xw.project.service.advertise.AdvEduLifeHallService;
import com.xw.project.entity.AdvEduLifeHall;

import java.util.List;

/**
 * @author Jy
 * @since 2020-06-30
 */
@Api(description = "生命教育体验馆管理")
@RestController
@RequestMapping("/advEduLifeHall")
@Slf4j
public class AdvEduLifeHallController extends BaseController {

    @Autowired
    private AdvEduLifeHallService advEduLifeHallService;

    @Autowired
    private TargetPositionService targetPositionService;

    @ApiOperation("分页")
    @GetMapping("/page")
    public RestResponse<IPage<AdvEduLifeHallVo>> page(AdvEduLifeHallDto advEduLifeHallDto) {
        try {
            return ResultGenerator.genSuccessResult(advEduLifeHallService.page(advEduLifeHallDto));
        } catch (Exception e) {
            log.error(DocConstant.search_error, e);
            return ResultGenerator.genFailResult(DocConstant.search_error);
        }
    }


    @ApiOperation("添加")
    @PostMapping("/add")
    public RestResponse<String> add(@RequestBody AdvEduLifeHallDto advEduLifeHallDto) {
        //防止新增传过来ID不为空
        if (advEduLifeHallDto.getId() != null) {
            advEduLifeHallDto.setId(null);
        }
        return advEduLifeHallService.add(advEduLifeHallDto);
    }

    @ApiOperation("修改")
    @PutMapping("/update")
    public RestResponse<String> update(@RequestBody AdvEduLifeHallDto advEduLifeHallDto) {

        return advEduLifeHallService.update(advEduLifeHallDto);
    }

    @ApiOperation("删除")
    @DeleteMapping("/delete/{id}")
    public RestResponse<String> delete(@PathVariable String id) {
        return advEduLifeHallService.delete(id);
    }

    @GetMapping(
            value = {"/{id}"},
            produces = {"application/json; charset=utf-8"}
    )
    @ApiOperation(
            value = "获取体验馆详情",
            notes = "获取体验馆详情",
            code = 200,
            produces = "application/json"
    )
    public RestResponse<AdvEduLifeHallVo> getByIdVo(@PathVariable String id) {
        return ResultGenerator.genSuccessResult(this.advEduLifeHallService.getByIdVo(id));
    }

    @GetMapping(value = "/listTreeArea")
    @ApiOperation(value = "查询省市区")
    public RestResponse<List<AdvEduLifeHallVo>> listTreeArea() {
        return ResultGenerator.genSuccessResult(advEduLifeHallService.listTreeArea());
    }

    @GetMapping(value = {"/list"}, produces = {"application/json; charset=utf-8"})
    @ApiOperation(value = "获取体验馆所有地点")
    public RestResponse listAllAddress() {
        return ResultGenerator.genSuccessResult(advEduLifeHallService.listAllAddress());
    }

}
package com.xw.project.controller.rescuetrain;

import com.xw.core.constant.DocConstant;
import com.xw.project.entity.TargetPosition;
import com.xw.project.service.common.TargetPositionService;
import com.xw.project.vo.RtmAedVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.xw.project.dto.RtmAedDto;
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
import com.xw.project.service.rescuetrain.RtmAedService;
import com.xw.project.entity.RtmAed;

import java.util.List;

/**
 * @author Jy
 * @since 2020-06-23
 */
@Api("AED管理")
@RestController
@RequestMapping("/rtmAed")
@Slf4j
public class RtmAedController extends BaseController {

    @Autowired
    private RtmAedService rtmAedService;

    @Autowired
    private TargetPositionService targetPositionService;

    @ApiOperation("分页")
    @GetMapping("/page")
    public RestResponse<IPage<RtmAedVo>> page(RtmAedDto rtmAedDto) {
        try {
            return ResultGenerator.genSuccessResult(rtmAedService.page(rtmAedDto));
        } catch (Exception e) {
            log.error(DocConstant.search_error, e);
            return ResultGenerator.genFailResult(DocConstant.search_error);
        }
    }


    @ApiOperation("添加")
    @PostMapping("/add")
    public RestResponse<String> add(@RequestBody RtmAedDto rtmAedDto) {
        //防止新增传过来ID不为空
        if (rtmAedDto.getId() != null){
            rtmAedDto.setId(null);
        }
        return rtmAedService.add(rtmAedDto);
    }

    @ApiOperation("修改")
    @PutMapping("/update")
    public RestResponse<String> update(@RequestBody RtmAedDto rtmAedDto) {
        return  rtmAedService.update(rtmAedDto);
    }

    @ApiOperation("删除")
    @DeleteMapping("/delete/{id}")
    public RestResponse<String> delete(@PathVariable String id) {
       return rtmAedService.delete(id);
    }

    @GetMapping(
            value = {"/{id}"},
            produces = {"application/json; charset=utf-8"}
    )
    @ApiOperation(
            value = "获取AED详情",
            notes = "获取AED详情",
            code = 200,
            produces = "application/json"
    )
    public RestResponse<RtmAedVo> getByIdVo(@PathVariable String id) {
        return ResultGenerator.genSuccessResult(this.rtmAedService.getByIdVo(id));
    }

    @GetMapping(value = "/listTreeArea")
    @ApiOperation(value = "查询省市区")
    public RestResponse<List<RtmAedVo>> listTreeArea() {
        return ResultGenerator.genSuccessResult(rtmAedService.listTreeArea());
    }

    @GetMapping(value = {"/list"}, produces = {"application/json; charset=utf-8"})
    @ApiOperation(value = "获取AED所有地点")
    public RestResponse listAllAddress() {
        return ResultGenerator.genSuccessResult(rtmAedService.listAllAddress());
    }

}
package com.xw.project.controller.common;

import com.xw.core.constant.DocConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.xw.project.dto.TargetPositionDto;
import lombok.extern.slf4j.Slf4j;
import com.xw.common.controller.BaseController;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xw.common.result.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.xw.common.result.ResultGenerator;
import com.xw.project.service.common.TargetPositionService;
import com.xw.project.entity.TargetPosition;

/**
* @author Jy
* @since 2020-06-28
*/
@Api(description = "有关天地图的基本信息管理" )
@RestController
@RequestMapping("/targetPosition" )
@Slf4j
public class TargetPositionController extends BaseController {

  @Autowired
  private TargetPositionService  targetPositionService;

  @ApiOperation("分页" )
  @GetMapping("/page" )
  public RestResponse< IPage< TargetPosition>> page(TargetPositionDto targetPositionDto) {
    try {
      return  ResultGenerator.genSuccessResult(targetPositionService.page(targetPositionDto));
    } catch (Exception e) {
      log.error(DocConstant.search_error,e);
      return ResultGenerator.genFailResult(DocConstant.search_error);
    }
  }


  @ApiOperation("添加" )
  @PostMapping("/add" )
  public RestResponse< String >  add(@RequestBody TargetPosition targetPosition) {
    try {
      targetPositionService.add(targetPosition);
      return ResultGenerator.genSuccessResult(DocConstant.add_success);
    } catch (Exception e) {
      log.error(DocConstant.add_error,e);
      return ResultGenerator.genFailResult(DocConstant.add_error);
    }
  }

  @ApiOperation("修改" )
  @PutMapping("/update" )
  public RestResponse< String >  update(@RequestBody TargetPosition targetPosition) {
    try {
      targetPositionService.update(targetPosition);
      return ResultGenerator.genSuccessResult(DocConstant.update_success);
    } catch (Exception e) {
      log.error(DocConstant.update_error,e);
      return ResultGenerator.genFailResult(DocConstant.update_error);
    }
  }

  @ApiOperation("删除" )
  @DeleteMapping("/delete" )
  public RestResponse< String > delete(String id) {
    try {
      targetPositionService.delete(id);
      return ResultGenerator.genSuccessResult(DocConstant.delete_success);
    } catch (Exception e) {
      log.error(DocConstant.delete_error,e);
      return ResultGenerator.genFailResult(DocConstant.delete_error);
    }
  }

}
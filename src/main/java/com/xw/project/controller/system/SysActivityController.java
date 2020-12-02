package com.xw.project.controller.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xw.common.result.RestResponse;
import com.xw.common.result.ResultGenerator;
import com.xw.common.util.StringUtil;
import com.xw.core.constant.DocConstant;
import com.xw.project.dto.SysActivityDto;
import com.xw.project.service.system.SysActivityService;
import com.xw.project.vo.SysActivityVo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/sysActivity")
public class SysActivityController {
    @Autowired
    SysActivityService sysActivityService;

    @GetMapping("/search")
    @ApiOperation("查询")
    public RestResponse<IPage<SysActivityVo>> search(SysActivityDto sysActivityDto) {
        return sysActivityService.listActivity(sysActivityDto);
    }

    @PostMapping(value = {"/addSysActivity"})
    @ApiOperation("新增")
    public RestResponse<String> addSysActivity(@Valid @RequestBody SysActivityVo sysActivityVo)  {
        return sysActivityService.addSysActivity(sysActivityVo);
    }

    @GetMapping("/getSysActivity")
    @ApiOperation("根据id查询活动详情")
    public RestResponse<SysActivityVo> getSysActivity(String id) {
        if(StringUtil.isNotEmpty(id)){
            return sysActivityService.getSysActivity(id);
        }else {
            return ResultGenerator.genFailResult(DocConstant.search_error);
        }
    }

    @PutMapping("/updateSysActivity")
    @ApiOperation("修改活动信息")
    public RestResponse<String> updateSysActivity(@Valid @RequestBody SysActivityVo sysActivityVo) {
        return sysActivityService.updateSysActivity(sysActivityVo);
    }

    @DeleteMapping("/deleteSysActivity")
    @ApiOperation("删除活动信息")
    public RestResponse<String> deleteSysActivity(String id) {
        if(StringUtil.isNotEmpty(id)){
            return sysActivityService.deleteSysActivity(id);
        }else {
            return ResultGenerator.genFailResult(DocConstant.delete_error);
        }
    }
}

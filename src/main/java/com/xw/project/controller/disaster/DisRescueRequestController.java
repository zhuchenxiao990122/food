package com.xw.project.controller.disaster;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xw.common.result.RestResponse;
import com.xw.common.result.ResultGenerator;
import com.xw.common.util.StringUtil;
import com.xw.core.constant.DocConstant;
import com.xw.project.dto.DisRescueRequestDto;
import com.xw.project.entity.DisRescueRequest;
import com.xw.project.service.disaster.DisRescueRequestService;
import com.xw.project.service.disaster.DisReserveNoticeService;
import com.xw.project.vo.DisRescueRequestVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.xw.common.controller.BaseController;

import javax.validation.Valid;
import java.util.List;
import java.util.zip.DeflaterInputStream;

/**
 * <p>
 * 救助请求controller
 * </p>
 *
 * @author caoshuhao
 * @since 2020-06-28
 */
@Slf4j
@RestController
@RequestMapping("/disRescueRequest")
@Api(
        value = "disRescueRequestController",
        tags = {"救助请求"}
)
public class DisRescueRequestController extends BaseController {

    @Autowired
    private DisRescueRequestService rescueRequestService;

    @GetMapping("/search")
    @ApiOperation(value = "搜索救助请求")
    public RestResponse<IPage<DisRescueRequestVo>> search(DisRescueRequestDto disRescueRequestDto) {
        return rescueRequestService.search(disRescueRequestDto);
    }

    @PostMapping("/add")
    @ApiOperation("新增救助请求")
    public RestResponse<String> addDisRescueRequest(@Valid @RequestBody DisRescueRequestVo disRescueRequestVo) {
        try {
            rescueRequestService.addDisRescueRequest(disRescueRequestVo);
            return ResultGenerator.genSuccessResult(DocConstant.add_success);
        } catch (Exception e) {
            log.error(DocConstant.add_error, e);
            return ResultGenerator.genFailResult(DocConstant.add_error);
        }
    }

    @GetMapping("/getRescueRequest")
    @ApiOperation("查看资助请求")
    public RestResponse<DisRescueRequestVo> getRescueRequest(String publishId, String applyNumber) {
        if(StringUtil.isNotEmpty(publishId)&&StringUtil.isNotEmpty(applyNumber)){
            return ResultGenerator.genSuccessResult(rescueRequestService.getRescueRequest(publishId, applyNumber));
        }else {
            return ResultGenerator.genFailResult(DocConstant.search_error);
        }
    }

    @GetMapping("/listPublishCode")
    @ApiOperation("获取上报编号")
    public RestResponse<List<String>> listPublishCode(String publishOrg) {
        if(StringUtil.isNotEmpty(publishOrg)){
            return rescueRequestService.listPublishCode(publishOrg);
        }else {
            return ResultGenerator.genFailResult(DocConstant.search_error);
        }
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除")
    public RestResponse<String> delete(String publishId, String applyNumber) {
        if(StringUtil.isNotEmpty(publishId)&&StringUtil.isNotEmpty(applyNumber)){
            return rescueRequestService.delete(publishId, applyNumber);
        }else {
            return ResultGenerator.genFailResult(DocConstant.delete_error);
        }
    }

}


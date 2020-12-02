package com.xw.project.controller.disaster;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xw.common.controller.BaseController;
import com.xw.common.result.RestResponse;
import com.xw.common.result.ResultGenerator;
import com.xw.core.constant.DocConstant;
import com.xw.project.dto.DisFundAllocationDto;
import com.xw.project.dto.DisFundAllocationFileDto;
import com.xw.project.entity.DisDonationFund;
import com.xw.project.entity.DisFundAllocation;
import com.xw.project.service.disaster.DisFundAllocationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author yuli
 * @since 2020-07-23
 */
@Api(description = "资金分配")
@RestController
@RequestMapping("/disFundAllocation")
@Slf4j
public class DisFundAllocationController extends BaseController {

    @Autowired
    private DisFundAllocationService disFundAllocationService;

    @ApiOperation("分页")
    @GetMapping("/page")
    public RestResponse<IPage<DisFundAllocation>> page(DisFundAllocationDto disFundAllocationDto) {

     return ResultGenerator.genSuccessResult(disFundAllocationService.page(disFundAllocationDto));

    }


    @ApiOperation("添加")
    @PostMapping("/add")
    public RestResponse<String> save(@Valid  @RequestBody DisFundAllocationFileDto disFundAllocationFileDto) {

          return   disFundAllocationService.save(disFundAllocationFileDto);
    }

    @ApiOperation("修改")
    @PutMapping("/update")
    public RestResponse<String> update(@RequestBody DisFundAllocation disFundAllocation) {
        try {
            disFundAllocationService.update(disFundAllocation);
            return ResultGenerator.genSuccessResult(DocConstant.update_success);
        } catch (Exception e) {
            log.error(DocConstant.update_error, e);
            return ResultGenerator.genFailResult(DocConstant.update_error);
        }
    }

    @ApiOperation("删除")
    @DeleteMapping("/delete")
    public RestResponse<String> delete(String id) {
        try {
            disFundAllocationService.delete(id);
            return ResultGenerator.genSuccessResult(DocConstant.delete_success);
        } catch (Exception e) {
            log.error(DocConstant.delete_error, e);
            return ResultGenerator.genFailResult(DocConstant.delete_error);
        }
    }

    @ApiOperation("根据金额刷选资金")
    @GetMapping("/listFund")
    public RestResponse<List<DisDonationFund>> listFund(BigDecimal amount, String projectId) {

        return disFundAllocationService.listFund(amount,projectId);

    }


}
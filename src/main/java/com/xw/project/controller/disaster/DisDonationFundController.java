package com.xw.project.controller.disaster;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xw.common.controller.BaseController;
import com.xw.common.result.RestResponse;
import com.xw.common.result.ResultGenerator;
import com.xw.core.constant.DocConstant;
import com.xw.project.dto.DisDonationFundDto;
import com.xw.project.dto.DisDonationFundFileDto;
import com.xw.project.entity.DisDonationFund;
import com.xw.project.service.disaster.DisDonationFundService;
import com.xw.project.vo.DisDonationFundVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yuli
 * @since 2020-07-16
 */
@Api(description = "捐赠资金")
@RestController
@RequestMapping("/disDonationFund")
@Slf4j
public class DisDonationFundController extends BaseController {

    @Autowired
    private DisDonationFundService disDonationFundService;

    @ApiOperation("分页")
    @GetMapping("/page")
    public RestResponse<IPage<DisDonationFundVo>> page(DisDonationFundDto disDonationFundDto) {
        return ResultGenerator.genSuccessResult(disDonationFundService.page(disDonationFundDto));
    }


    @ApiOperation("添加")
    @PostMapping("/add")
    public RestResponse<String> add(@RequestBody DisDonationFundFileDto disDonationFundFileDto) {
        return disDonationFundService.add(disDonationFundFileDto);
    }

    @ApiOperation("修改")
    @PutMapping("/update")
    public RestResponse<String> update(@RequestBody DisDonationFund disDonationFund) {
        try {
            disDonationFundService.update(disDonationFund);
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
            disDonationFundService.delete(id);
            return ResultGenerator.genSuccessResult(DocConstant.delete_success);
        } catch (Exception e) {
            log.error(DocConstant.delete_error, e);
            return ResultGenerator.genFailResult(DocConstant.delete_error);
        }
    }

}
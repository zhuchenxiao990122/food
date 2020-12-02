package com.xw.project.controller.disaster;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xw.common.controller.BaseController;
import com.xw.common.result.RestResponse;
import com.xw.common.result.ResultGenerator;
import com.xw.core.constant.DocConstant;
import com.xw.project.dto.DisMaterialDonationRequireDto;
import com.xw.project.dto.DisMaterialDonationRequireMaterialDto;
import com.xw.project.entity.DisMaterialDonationRequire;
import com.xw.project.service.disaster.DisMaterialDonationRequireService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yuli
 * @since 2020-06-30
 */
@Api(description = "")
@RestController
@RequestMapping("/disMaterialDonationRequire")
@Slf4j
public class DisMaterialDonationRequireController extends BaseController {

    @Autowired
    private DisMaterialDonationRequireService disMaterialDonationRequireService;

    @ApiOperation("分页")
    @GetMapping("/page")
    public RestResponse<IPage<DisMaterialDonationRequire>> page(DisMaterialDonationRequireDto disMaterialDonationRequireDto) {
        try {
            return ResultGenerator.genSuccessResult(disMaterialDonationRequireService.page(disMaterialDonationRequireDto));
        } catch (Exception e) {
            log.error(DocConstant.search_error, e);
            return ResultGenerator.genFailResult(DocConstant.search_error);
        }
    }


    @ApiOperation("新增物资捐赠要求")
    @PostMapping("/save")
    public RestResponse<String> add(@RequestBody DisMaterialDonationRequireMaterialDto disMaterialDonationRequireMaterialDto) {
        try {
            disMaterialDonationRequireService.save(disMaterialDonationRequireMaterialDto);
            return ResultGenerator.genSuccessResult(DocConstant.add_success);
        } catch (Exception e) {
            log.error(DocConstant.add_error, e);
            return ResultGenerator.genFailResult(DocConstant.add_error);
        }
    }

    @ApiOperation("修改")
    @PutMapping("/update")
    public RestResponse<String> update(@RequestBody DisMaterialDonationRequire disMaterialDonationRequire) {
        try {
            disMaterialDonationRequireService.update(disMaterialDonationRequire);
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
            disMaterialDonationRequireService.delete(id);
            return ResultGenerator.genSuccessResult(DocConstant.delete_success);
        } catch (Exception e) {
            log.error(DocConstant.delete_error, e);
            return ResultGenerator.genFailResult(DocConstant.delete_error);
        }
    }

}
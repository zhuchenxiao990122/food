package com.xw.project.controller.disaster;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xw.common.controller.BaseController;
import com.xw.common.result.RestResponse;
import com.xw.common.result.ResultGenerator;
import com.xw.core.constant.DocConstant;
import com.xw.project.dto.DisDonationIntentionDto;
import com.xw.project.entity.ComFile;
import com.xw.project.entity.DisDonationIntention;
import com.xw.project.service.disaster.DisDonationIntentionService;
import com.xw.project.vo.DisDonationIntentionVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yuli
 * @since 2020-07-14
 */
@Api(description = "捐赠意向")
@RestController
@RequestMapping("/disDonationIntention")
@Slf4j
public class DisDonationIntentionController extends BaseController {

    @Autowired
    private DisDonationIntentionService othDonationIntentionService;

    @ApiOperation("分页")
    @GetMapping("/page")
    public RestResponse<IPage<DisDonationIntentionVo>> page(DisDonationIntentionDto disDonationIntentionDto) {
        return ResultGenerator.genSuccessResult(othDonationIntentionService.page(disDonationIntentionDto));
    }


    @ApiOperation("添加基本信息")
    @PostMapping("/save")
    public RestResponse<String> add(@RequestBody DisDonationIntention disDonationIntention) {
        return othDonationIntentionService.add(disDonationIntention);
    }

    @ApiOperation("修改")
    @PutMapping("/update")
    public RestResponse<String> update(@RequestBody DisDonationIntention disDonationIntention) {
        try {
            othDonationIntentionService.update(disDonationIntention);
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
            othDonationIntentionService.delete(id);
            return ResultGenerator.genSuccessResult(DocConstant.delete_success);
        } catch (Exception e) {
            log.error(DocConstant.delete_error, e);
            return ResultGenerator.genFailResult(DocConstant.delete_error);
        }
    }

    @ApiOperation("保存文件（单）")
    @PutMapping("/addFile")
    public RestResponse<String> addFile(ComFile comFile) {
        if (null == comFile) {
            return ResultGenerator.genFailResult(DocConstant.add_error);
        }
        return othDonationIntentionService.addFile(comFile);
    }

    @ApiOperation("删除文件（单）")
    @PutMapping("/deleteFile")
    public RestResponse<String> deleteFile(ComFile comFile) {
        if (null == comFile) {
            return ResultGenerator.genFailResult(DocConstant.delete_error);
        }
        return othDonationIntentionService.deleteFile(comFile);
    }
}
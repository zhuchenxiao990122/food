package com.xw.project.controller.disaster;

import com.xw.common.controller.BaseController;
import com.xw.common.result.RestResponse;
import com.xw.common.result.ResultGenerator;
import com.xw.common.util.StringUtil;
import com.xw.core.constant.DocConstant;
import com.xw.project.dto.DisDonationIntentionDetailDto;
import com.xw.project.entity.DisDonationIntentionDetail;
import com.xw.project.service.disaster.DisDonationIntentionDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yuli
 * @since 2020-07-14
 */
@Api(description = "")
@RestController
@RequestMapping("/disDonationIntentionDetail")
@Slf4j
public class DisDonationIntentionDetailController extends BaseController {

    @Autowired
    private DisDonationIntentionDetailService othDonationIntentionDetailService;

    @ApiOperation("新增")
    @PostMapping("/save")
    public RestResponse<String> save(@RequestBody DisDonationIntentionDetailDto disDonationIntentionDetailDto) {
        String intentionId = disDonationIntentionDetailDto.getIntentionId();
        List<DisDonationIntentionDetail> othDonationIntentionDetails = disDonationIntentionDetailDto.getOthDonationIntentionDetails();
        if (StringUtil.isEmpty(intentionId)) {
            return ResultGenerator.genFailResult(DocConstant.primary_key_error);
        }
        if (null == othDonationIntentionDetails) {
            return ResultGenerator.genFailResult(DocConstant.object_error);
        }
        return othDonationIntentionDetailService.save(disDonationIntentionDetailDto);

    }

    @ApiOperation("修改")
    @PutMapping("/update")
    public RestResponse<String> update(@RequestBody DisDonationIntentionDetail disDonationIntentionDetail) {
        try {
            othDonationIntentionDetailService.update(disDonationIntentionDetail);
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
            othDonationIntentionDetailService.delete(id);
            return ResultGenerator.genSuccessResult(DocConstant.delete_success);
        } catch (Exception e) {
            log.error(DocConstant.delete_error, e);
            return ResultGenerator.genFailResult(DocConstant.delete_error);
        }
    }

}
package com.xw.project.controller.disaster;

import com.xw.common.controller.BaseController;
import com.xw.common.result.RestResponse;
import com.xw.common.result.ResultGenerator;
import com.xw.common.util.StringUtil;
import com.xw.core.constant.DocConstant;
import com.xw.project.dto.DisDonationProjectDetailDto;
import com.xw.project.entity.DisDonationProjectDetail;
import com.xw.project.service.disaster.DisDonationProjectDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yuli
 * @since 2020-07-01
 */
@Api(description = "")
@RestController
@RequestMapping("/disDonationProjectDetail")
@Slf4j
public class DisDonationProjectDetailController extends BaseController {

    @Autowired
    private DisDonationProjectDetailService disDonationProjectDetailService;

    @ApiOperation("查询")
    @GetMapping("/search")
    public List<DisDonationProjectDetail> search(String projectId) {

        return disDonationProjectDetailService.search(projectId);

    }

    @ApiOperation("添加")
    @PostMapping("/save")
    public RestResponse<String> add(@RequestBody DisDonationProjectDetailDto disDonationProjectDetailDto) {
        if (null == disDonationProjectDetailDto || null == disDonationProjectDetailDto.getDisDonationProjectDetail()) {
            return ResultGenerator.genFailResult("新增数据错误");
        }
        if (StringUtil.isEmpty(disDonationProjectDetailDto.getDonationType())) {
            return ResultGenerator.genFailResult("捐赠类型不能为空");
        }
        if (StringUtil.isEmpty(disDonationProjectDetailDto.getProjectId())) {
            return ResultGenerator.genFailResult("项目id不能为空");
        }
        return disDonationProjectDetailService.save(disDonationProjectDetailDto);
    }

    @ApiOperation("修改")
    @PutMapping("/update")
    public RestResponse<String> update(@RequestBody DisDonationProjectDetail disDonationProjectDetail) {
        try {
            disDonationProjectDetailService.update(disDonationProjectDetail);
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
            disDonationProjectDetailService.delete(id);
            return ResultGenerator.genSuccessResult(DocConstant.delete_success);
        } catch (Exception e) {
            log.error(DocConstant.delete_error, e);
            return ResultGenerator.genFailResult(DocConstant.delete_error);
        }
    }

}
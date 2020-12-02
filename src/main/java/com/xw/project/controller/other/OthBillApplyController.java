package com.xw.project.controller.other;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xw.common.result.RestResponse;
import com.xw.common.result.ResultGenerator;
import com.xw.project.entity.OthBillApply;
import com.xw.project.service.other.OthBillApplyService;
import com.xw.project.vo.OthBillApplySearchVo;
import com.xw.project.vo.OthBillApplyVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author dy
 * @since 2020-02-19
 */
@RestController
@Api(value = "票据申请")
@RequestMapping("/othBillApply")
public class OthBillApplyController {
    @Autowired
    private OthBillApplyService othBillApplyService;

    @GetMapping(value = {"/listBillApply"})
    @ApiOperation("获取票据申请详列表")
    public RestResponse<IPage<OthBillApplyVo>> listBillApply(@RequestParam(required = false, value = "applyDate") List<String> applyDate, OthBillApplySearchVo OthBillApplySearchVo) {
        try {
            return ResultGenerator.genSuccessResult(othBillApplyService.listBillApply(applyDate, OthBillApplySearchVo));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult("获取票据申请详列表失败");
        }
    }

    @PostMapping(value = {"/addBillApply"})
    @ApiOperation(value = "添加票据申请信息")
    public RestResponse<String> addBillApply(OthBillApply othBillApply) {
        try {
            int result = othBillApplyService.addBillApply(othBillApply);
            if (result > 0) {
                return ResultGenerator.genSuccessResult();
            } else {
                return ResultGenerator.genFailResult("添加票据申请信息失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult("添加票据申请信息失败");
        }
    }

    @RequestMapping("/billApplyExport")
    @ApiOperation(value = "票据申请导出")
    public void billApplyExport(@RequestParam(value = "applyDate") List<String> applyDate) throws Exception {
        othBillApplyService.billApplyExport(applyDate);
    }
}

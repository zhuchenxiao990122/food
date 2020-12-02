package com.xw.project.controller.disaster;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xw.common.result.RestResponse;
import com.xw.common.result.ResultGenerator;
import com.xw.project.service.disaster.DisStockInDetailService;
import com.xw.project.vo.DisDonatedMaterialDetailVo;
import com.xw.project.vo.DisDonatedMaterialsVo;
import com.xw.project.vo.DisStockWaitInVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yuli
 * @since 2020-04-13
 */
@RestController
@Api(value = "物资入库详情")
@RequestMapping("/disStockInDetail")
public class DisStockInDetailController {
    @Autowired
    private DisStockInDetailService disStockInDetailService;

    @RequestMapping(value = {"/waitInListDetails"})
    @ApiOperation(value = "获取待入库信息详情")
    public RestResponse<List<DisDonatedMaterialDetailVo>> getWaitInListDetails(String noticeId) {
        return ResultGenerator.genSuccessResult(disStockInDetailService.getWaitInListDetails(noticeId));
    }
}

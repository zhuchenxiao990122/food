package com.xw.project.controller.disaster;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xw.common.result.RestResponse;
import com.xw.common.result.ResultGenerator;
import com.xw.project.entity.DisStockIn;
import com.xw.project.service.disaster.DisStockInService;
import com.xw.project.vo.DisDonatedMaterialDetailVo;
import com.xw.project.vo.DisStockWaitInVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yuli
 * @since 2020-04-13
 */
@RestController
@Api(value = "物资入库")
@RequestMapping("/disStockIn")
public class DisStockInController {
    @Autowired
    private DisStockInService disStockInService;

    @RequestMapping(value = {"/waitInList"})
    @ApiOperation(value = "获取待入库信息")
    public RestResponse<IPage<DisStockWaitInVo>> getWaitInList(Integer current, Integer size) {
        return ResultGenerator.genSuccessResult(disStockInService.getWaitInList(current,size));
    }

    @RequestMapping(value = {"/inList"})
    @ApiOperation(value = "获取已入库信息")
    public RestResponse<IPage<DisStockWaitInVo>> getInList(Integer current, Integer size) {
        return ResultGenerator.genSuccessResult(disStockInService.getInList(current,size));
    }

    @RequestMapping(value = {"/stockIn"})
    @ApiOperation(value = "物资入库")
    public RestResponse<String> saveMaterials(@RequestBody Map<String, Object> map) {
        return ResultGenerator.genSuccessResult(disStockInService.stockIn(map));
    }

}

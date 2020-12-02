package com.xw.project.controller.disaster;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xw.common.result.RestResponse;
import com.xw.common.result.ResultGenerator;
import com.xw.common.util.StringUtil;
import com.xw.core.constant.DocConstant;
import com.xw.project.dto.DisMaterialWarehouseDto;
import com.xw.project.entity.DisMaterialWarehouse;
import com.xw.project.service.disaster.DisMaterialWarehouseService;
import com.xw.project.vo.DisMaterialCategorysVo;
import com.xw.project.vo.DisMaterialWarehouseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * <p>救灾救援-仓库管理-仓库信息管理-前端控制器
 *
 * @author weiLiang
 * @since 2020-07-13
 */
@Api(
        value = "仓库管理",
        description = "救灾救援-仓库管理-仓库信息"
)
@RestController
@RequestMapping("/disMaterialWarehouse")
@SuppressWarnings("unchecked")
public class DisMaterialWarehouseController {
    @Autowired
    private DisMaterialWarehouseService disMaterialWarehouseService;

    @ApiOperation(
            value = "获取仓库信息",
            notes = "根据搜索条件进行搜索，如果都为空，则搜索出所有仓库"
    )
    @GetMapping(value = {"/pageListWarehouseInfo"})
    public RestResponse<IPage<DisMaterialWarehouseVo>> pageListWarehouseInfo(DisMaterialWarehouseDto disMaterialWarehouseDto) {
        return ResultGenerator.genSuccessResult(disMaterialWarehouseService.pageListWarehouseInfo(disMaterialWarehouseDto));
    }

    @ApiOperation(
            value = "获取组织所在区域行政编码",
            notes = "获取组织所在区域行政编码"
    )
    @GetMapping(value = {"/getAreaId"})
    public RestResponse<String> getAreaId(String orgId) {
        if(StringUtil.isNotEmpty(orgId)){
            String result = disMaterialWarehouseService.getAreaId(orgId);
            if(StringUtil.isNotEmpty(result)){
                return ResultGenerator.genSuccessResult(result);
            }
            return ResultGenerator.genFailResult(DocConstant.search_error);
        }
        return ResultGenerator.genFailResult(DocConstant.id_empty);

    }

    @ApiOperation(
            value = "新增仓库",
            notes = "保存仓库信息"
    )
    @PostMapping(value = {"/addWarehouseInfo"})
    public RestResponse<String> addWarehouseInfo(@Valid @RequestBody DisMaterialWarehouse disMaterialWarehouse) {
        Integer result = disMaterialWarehouseService.addWarehouseInfo(disMaterialWarehouse);
        if(result > 0){
            return ResultGenerator.genSuccessResult(DocConstant.add_success);
        }
        return ResultGenerator.genFailResult(DocConstant.add_error);
    }

    @ApiOperation(
            value = "删除仓库信息",
            notes = "根据 Id 删除对应仓库的信息（逻辑删除 del_flag = 1）"
    )
    @DeleteMapping(value = {"/deleteWarehouseInfoById"})
    public RestResponse<String> deleteWarehouseInfoById(String id) {
        if(StringUtil.isNotEmpty(id)){
            Integer result = disMaterialWarehouseService.deleteWarehouseInfoById(id);
            if(result > 0){
                return ResultGenerator.genSuccessResult(DocConstant.delete_success);
            }
            return ResultGenerator.genFailResult(DocConstant.delete_error);
        }
        return ResultGenerator.genFailResult(DocConstant.id_empty);

    }

    @ApiOperation(
            value = "修改仓库信息",
            notes = "修改仓库信息"
    )
    @PutMapping(value = {"/updateWarehouseInfo"})
    public RestResponse<String> updateWareHouseInfo(@Valid @RequestBody DisMaterialWarehouse disMaterialWarehouse) {
        Integer result = disMaterialWarehouseService.updateWareHouseInfo(disMaterialWarehouse);
        if(result > 0){
            return ResultGenerator.genSuccessResult(DocConstant.update_success);
        }
        return ResultGenerator.genFailResult(DocConstant.update_error);

    }
}

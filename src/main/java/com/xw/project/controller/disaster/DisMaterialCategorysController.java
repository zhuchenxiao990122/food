package com.xw.project.controller.disaster;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xw.common.result.RestResponse;
import com.xw.common.result.ResultGenerator;
import com.xw.common.util.StringUtil;
import com.xw.core.constant.DocConstant;
import com.xw.project.entity.DisMaterialCategorys;
import com.xw.project.service.disaster.DisDonatedMaterialsService;
import com.xw.project.service.disaster.DisMaterialCategorysService;
import com.xw.project.vo.DisMaterialCategorysVo;
import com.xw.system.vo.OrganizeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>救灾救援-仓库管理-物资种类管理-前端控制器
 *
 * @author yuli
 * @since 2020-04-09
 */
@Api(
        value = "物资种类管理",
        description = "救灾救援-仓库管理-物资类别"
)
@RestController
@RequestMapping("/disMaterialCategorys")
@SuppressWarnings("unchecked")
public class DisMaterialCategorysController {
    @Autowired
    private DisMaterialCategorysService disMaterialCategorysService;

    @ApiOperation(
            value = "获取物资类别",
            notes = "对物资类型进行简单的递归处理，形成多级列表返回"
    )
    @GetMapping(value = {"/pageListCategory"})
    public RestResponse<IPage<DisMaterialCategorysVo>> pageListCategory(String category, String code) {
        return ResultGenerator.genSuccessResult(disMaterialCategorysService.pageListCategory(category, code));
    }

    @ApiOperation(
            value = "新增物资类别",
            notes = "对父类的 Ids 做一个拼接处理"
    )
    @PostMapping(value = {"/addCategory"})
    public RestResponse<String> addCategory(@Valid @RequestBody DisMaterialCategorys disMaterialCategorys) {
        Integer result = disMaterialCategorysService.addCategory(disMaterialCategorys);
        if (result > 0) {
            return ResultGenerator.genSuccessResult(DocConstant.add_success);
        }
        return ResultGenerator.genFailResult(DocConstant.add_error);
    }

    @ApiOperation(
            value = "删除物资类别",
            notes = "根据 Id 删除对应物资类别（逻辑删除 del_flag = 1）"
    )
    @DeleteMapping(value = {"/{id}"})
    public RestResponse<String> deleteMaterialCategoryById(@PathVariable("id") String id) {
        if (StringUtil.isNotEmpty(id)) {
            Integer result = disMaterialCategorysService.deleteMaterialCategoryById(id);
            if (result > 0) {
                return ResultGenerator.genSuccessResult(DocConstant.delete_success);
            }
            return ResultGenerator.genFailResult(DocConstant.delete_error);
        }
        return ResultGenerator.genFailResult(DocConstant.id_empty);
    }

    @ApiOperation(
            value = "修改物资信息",
            notes = "修改物资信息"
    )
    @PutMapping(value = {"/updateCategory"})
    public RestResponse<String> updateCategory(@Valid @RequestBody DisMaterialCategorys disMaterialCategorys) {
        Integer result = disMaterialCategorysService.updateCategory(disMaterialCategorys);
        if (result > 0) {
            return ResultGenerator.genSuccessResult(DocConstant.delete_success);
        }
        return ResultGenerator.genFailResult(DocConstant.delete_error);
    }

    @RequestMapping(value = {"/categoryList"})
    @ApiOperation(value = "获取物资类别")
    public RestResponse<IPage<DisMaterialCategorysVo>> categoryList() {
        return ResultGenerator.genSuccessResult(disMaterialCategorysService.categoryList());
    }
}

package com.xw.project.controller.common;

import com.xw.common.controller.BaseController;
import com.xw.common.result.RestResponse;
import com.xw.common.result.ResultGenerator;
import com.xw.core.constant.DocConstant;
import com.xw.project.dto.ComFileDto;
import com.xw.project.service.common.ComFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author yuli
 * @since 2020-07-10
 */
@Api(description = "通用文件管理")
@RestController
@RequestMapping("/comFile")
@Slf4j
public class ComFileController extends BaseController {

    @Autowired
    private ComFileService comFileService;

    @ApiOperation("查询")
    @GetMapping("/search")
    public RestResponse<List<String>> search(@NotNull String id) {
        return ResultGenerator.genSuccessResult(comFileService.search(id));
    }

    @ApiOperation("添加或修改")
    @PostMapping("/addOrUpdate")
    public RestResponse<String> save(@RequestBody ComFileDto comFileDto) {
        if (comFileDto.getFileIds().size() == 0) {
            return ResultGenerator.genFailResult(DocConstant.list_size_error);
        }
        return comFileService.save(comFileDto);
    }

    @ApiOperation("删除")
    @DeleteMapping("/delete")
    public RestResponse<String> delete(@NotNull String id) {
        return comFileService.delete(id);
    }

}
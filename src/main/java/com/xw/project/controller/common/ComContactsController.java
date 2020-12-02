package com.xw.project.controller.common;


import com.xw.common.result.RestResponse;
import com.xw.common.result.ResultGenerator;
import com.xw.project.service.common.ComContactsService;
import com.xw.project.vo.ComContactsVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yuli
 * @since 2020-04-28
 */
@RestController
@RequestMapping("/comContacts")
public class ComContactsController {
    @Autowired
    private ComContactsService comContactsService;

    @GetMapping(value = {"/listManufacturer"})
    @ApiOperation(value = "根据contactId获取详细信息")
    public RestResponse<List<ComContactsVo>> getListByContactId() {
        return ResultGenerator.genSuccessResult(comContactsService.listManufacturer());
    }
}

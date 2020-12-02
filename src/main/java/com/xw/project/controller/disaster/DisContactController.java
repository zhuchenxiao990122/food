package com.xw.project.controller.disaster;


import com.xw.common.controller.BaseController;
import com.xw.common.result.RestResponse;
import com.xw.common.result.ResultGenerator;
import com.xw.project.service.disaster.DisContactService;
import com.xw.project.vo.DisContactVo;
import io.swagger.annotations.Api;
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
 * @since 2020-04-20
 */
@RestController
@RequestMapping("/disContact")
@Api(value = "联系人通用信息")
public class DisContactController extends BaseController {
    @Autowired
    private DisContactService disContactService;

    @GetMapping(value = {"/list"})
    @ApiOperation(value = "根据contactId获取详细信息")
    public RestResponse<List<DisContactVo>> getListByContactId(String id) {
        return ResultGenerator.genSuccessResult(disContactService.getListByContactId(id));
    }
}

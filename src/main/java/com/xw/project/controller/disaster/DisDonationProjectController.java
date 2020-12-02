package com.xw.project.controller.disaster;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xw.common.controller.BaseController;
import com.xw.common.entity.User;
import com.xw.common.result.RestResponse;
import com.xw.common.result.ResultGenerator;
import com.xw.common.util.StringUtil;
import com.xw.core.constant.DocConstant;
import com.xw.project.dto.DisDonationProjectDto;
import com.xw.project.entity.DisDonationProject;
import com.xw.project.service.disaster.DisDonationProjectService;
import com.xw.project.vo.DisDonationProjectVo;
import com.xw.system.util.AppUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author yuli
 * @since 2020-06-24
 */
@Api(description = "")
@RestController
@RequestMapping("/disDonationProject")
@Slf4j
public class DisDonationProjectController extends BaseController {

    @Autowired
    private DisDonationProjectService disDonationProjectService;
    @Autowired
    private AppUtil appUtil;

    @ApiOperation("分页")
    @GetMapping("/page")
    public RestResponse<IPage<DisDonationProjectVo>> page(DisDonationProjectDto disDonationProjectDto) {
        return ResultGenerator.genSuccessResult(disDonationProjectService.page(disDonationProjectDto));
    }

    @ApiOperation("保存")
    @PostMapping("/save")
    public RestResponse<String> add(@RequestBody DisDonationProject disDonationProject) {
        return disDonationProjectService.add(disDonationProject);
    }

    @ApiOperation("删除")
    @DeleteMapping("/delete")
    public RestResponse<String> delete(String id) {
        if (StringUtil.isEmpty(id)) {
            return ResultGenerator.genFailResult(DocConstant.delete_error);
        }
        return disDonationProjectService.delete(id);
    }

    @ApiOperation("启动工作流")
    @GetMapping("/applyStart")
    public RestResponse<String> applyStart(String id) {
        return disDonationProjectService.applyStart(id);
    }

    @ApiOperation("查看审批任务--分页")
    @GetMapping("/pageApprovalTask")
    public RestResponse<IPage<DisDonationProjectVo>> pageApprovalTask(int current, int size) {
        return ResultGenerator.genSuccessResult(disDonationProjectService.pageApprovalTask(current, size));
    }

    @ApiOperation("工作流审批")
    @GetMapping("/approval")
    public RestResponse<String> approval(String id, String state, String comment) {
        return disDonationProjectService.approval(id, state, comment);
    }

    @ApiOperation("页面发布展示模块")
    @GetMapping("/pagePublish")
    public RestResponse<IPage<DisDonationProjectVo>> pagePublish(int current, int size) {
        IPage<DisDonationProjectVo> disDonationProjectIPage = disDonationProjectService.pagePublish(current, size);
        return ResultGenerator.genSuccessResult(disDonationProjectIPage);
    }

    @ApiOperation("发布项目")
    @GetMapping("/projectPublish")
    public RestResponse<String> projectPublish(String id) {
        return disDonationProjectService.projectPublish(id);
    }

    @ApiOperation("撤销项目模块")
    @GetMapping("/cancelProject")
    public RestResponse<IPage<DisDonationProject>> cancelProject(String id) {
        return disDonationProjectService.cancelProject(id);
    }

    @ApiOperation("项目申请分页")
    @GetMapping("/pageApply")
    public RestResponse<IPage<DisDonationProjectVo>> pageApply(DisDonationProjectDto disDonationProjectDto) {
        return ResultGenerator.genSuccessResult(disDonationProjectService.pageApply(disDonationProjectDto));
    }

    @ApiOperation("捐款项目")
    @GetMapping("/fundList")
    public RestResponse<List<Map<String, String>>> fundList(String orgId) {
        if (StringUtil.isEmpty(orgId)) {
            User user = appUtil.getUser();
            orgId = user.getUserOrg();
        }
        return ResultGenerator.genSuccessResult(disDonationProjectService.fundList(orgId));
    }

    @ApiOperation("捐物项目")
    @GetMapping("/materialList")
    public RestResponse<List<Map<String, String>>> materialList(String orgId) {
        if (StringUtil.isEmpty(orgId)) {
            User user = appUtil.getUser();
            orgId = user.getUserOrg();
        }
        return ResultGenerator.genSuccessResult(disDonationProjectService.materialList(orgId));
    }
}
package com.xw.project.controller.organize;

import com.xw.common.entity.User;
import com.xw.common.result.RestResponse;
import com.xw.common.result.ResultGenerator;
import com.xw.common.util.StringUtil;

import com.xw.core.constant.DocConstant;
import com.xw.project.entity.OrgRedCross;
import com.xw.project.entity.TreeNode;
import com.xw.project.service.organize.OrganizeManageService;
import com.xw.project.vo.OrgInternalUserVo;
import com.xw.project.vo.OrganizeInternalVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.runtime.Execution;
import org.bouncycastle.cert.ocsp.Req;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.elasticsearch.rest.RestClientProperties;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping({"/organizeManage"})
@Api(value = "组织管理")
@Slf4j
public class OrganizeManageController {
    @Autowired
    private OrganizeManageService organizeManageService;

    @GetMapping(value = {"/listOrganize"})
    @ApiOperation(value = "获取机构列表")
    public RestResponse<List<TreeNode>> listOrganize() {
        return ResultGenerator.genSuccessResult(organizeManageService.listOrganize());
    }

    @GetMapping(value = {"/organizeInfo"})
    @ApiOperation(value = "获取组织机构详情")
    public RestResponse<OrgRedCross> organizeDetailInfo( String treeId) {
        if (StringUtil.isNotEmpty(treeId)) {
            return ResultGenerator.genSuccessResult(organizeManageService.organizeDetailInfo(treeId));
        }
        return ResultGenerator.genFailResult(DocConstant.update_error);
    }

    @PutMapping(value = {"/updateOrganizeBase"})
    @ApiOperation(value = "组织基本信息修改")
    public RestResponse<String> updateOrganizeBase(@Valid @RequestBody OrgRedCross orgRedCross) {
        organizeManageService.updateOrganizeBase(orgRedCross);
        return ResultGenerator.genSuccessResult(DocConstant.update_success);
    }

    @GetMapping(value = {"/listOrganizationUnit"})
    @ApiOperation(value = "获取组织内部机构")
    public RestResponse<List<OrganizeInternalVo>> listOrganizationUnit(String parentId, String orgType) {
        if (StringUtil.isNotEmpty(parentId) && StringUtil.isNotEmpty(orgType)) {
            return organizeManageService.listOrganizationUnit(parentId, orgType);
        } else {
            return ResultGenerator.genFailResult(DocConstant.search_error);
        }
    }

    @DeleteMapping(value = {"/deleteInternalOrganize/{orgId}"})
    @ApiOperation("删除机构")
    public RestResponse<String> deleteInternal(@PathVariable String orgId) {
        if (StringUtil.isNotEmpty(orgId)) {
            return organizeManageService.deleteInternalOrganize(orgId);
        } else {
            return ResultGenerator.genFailResult(DocConstant.delete_error);
        }
    }

    @PutMapping(value = {"/updateInternalOrganize"})
    @ApiOperation("修该组织机构")
    public RestResponse<String> updateInternalOrganize(@Valid @RequestBody OrgInternalUserVo orgInternalUserVo) {
        if(StringUtil.isNotEmpty(orgInternalUserVo.getOrgId())){
            return organizeManageService.updateInternalOrganize(orgInternalUserVo);
        }else {
            return ResultGenerator.genFailResult(DocConstant.update_error);
        }
    }

    @GetMapping(value = {"/listOrgUser"})
    @ApiOperation("所属组织的用户")
    public RestResponse<List<OrganizeInternalVo>> listOrgUser(String orgId) {
        if(StringUtil.isNotEmpty(orgId)){
            return organizeManageService.listOrgUser(orgId);
        } else {
            return ResultGenerator.genFailResult(DocConstant.search_error);
        }
    }

    @PostMapping(value = {"/addInternalOrg"})
    @ApiOperation("新增组织机构")
    public RestResponse<String> addInternalOrg(@Valid @RequestBody OrgInternalUserVo orgInternalUserVo) {
            return organizeManageService.addInternalOrg(orgInternalUserVo);
    }
}

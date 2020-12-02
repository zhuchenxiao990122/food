package com.xw.project.controller.organize;


import com.xw.common.result.RestResponse;
import com.xw.common.result.ResultGenerator;
import com.xw.common.util.StringUtil;
import com.xw.core.constant.DocConstant;
import com.xw.project.entity.OrgHistory;
import com.xw.project.entity.OrgMember;
import com.xw.project.service.organize.OrgHistoryService;
import com.xw.project.vo.OrgHistoryVo;
import com.xw.project.vo.OrgInternalUserVo;
import com.xw.project.vo.OrganizeInternalVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.runtime.directive.Define;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.xw.common.controller.BaseController;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 历届信息
 * </p>
 *
 * @author caoshuhao
 * @since 2020-06-22
 */
@Slf4j
@RestController
@RequestMapping("/orgHistory")
@Api(value = "历届信息" ,description = "历届信息")
public class OrgHistoryController extends BaseController {

    @Autowired
    private OrgHistoryService orgHistoryService;

    @GetMapping("/listOrgHistory")
    @ApiOperation("历届组织")
    public RestResponse<List<OrgHistory>> listOrgHistory(String parentId) {
        if (StringUtil.isNotEmpty(parentId)) {
            return orgHistoryService.listOrgHistory(parentId);
        } else {
            return ResultGenerator.genFailResult(DocConstant.search_error);
        }
    }

    @GetMapping("/listOrganizeMember")
    @ApiOperation("获取历届成员信息")
    public RestResponse<List<OrgMember>> listOrganizeMember(String orgId) {
        if (StringUtil.isNotEmpty(orgId)) {
            return orgHistoryService.listOrganizeMember(orgId);
        } else {
            return ResultGenerator.genFailResult(DocConstant.search_error);
        }
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除历届机构")
    public RestResponse<String> delete(@PathVariable String id) {
        if (StringUtil.isNotEmpty(id)) {
            return orgHistoryService.delete(id);
        } else {
            return ResultGenerator.genFailResult(DocConstant.delete_error);
        }
    }

    @PutMapping(value = {"/updateHistory"})
    @ApiOperation("修改历届组织信息")
    public RestResponse<String> updateHistory(@Valid @RequestBody OrgHistoryVo orgHistoryVo) {
        return orgHistoryService.updateHistory(orgHistoryVo);
    }

    @PostMapping(value = {"/addOrgHistory"})
    @ApiOperation("历届组织新增")
    public RestResponse<String> addOrgHistory(@Valid @RequestBody OrgHistoryVo orgHistoryVo) {
        return orgHistoryService.addOrgHistory(orgHistoryVo);
    }

    @PutMapping("/transition")
    @ApiOperation("组织换届")
    public RestResponse<String> transition(@Valid @RequestBody OrgHistoryVo orgHistoryVo) {
        return orgHistoryService.transition(orgHistoryVo);
    }

}


package com.xw.project.controller.organize;

import com.xw.common.controller.BaseController;
import com.xw.common.util.StringUtil;
import com.xw.core.constant.DocConstant;
import com.xw.project.vo.OrganizeInternalVo;
import com.xw.project.vo.OrganizeMemberVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.xw.project.dto.OrgMemberDto;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xw.common.result.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.xw.common.result.ResultGenerator;
import com.xw.project.service.organize.OrgMemberService;
import com.xw.project.entity.OrgMember;

import javax.validation.Valid;
import java.util.List;

/**
 * @author yuli
 * @since 2020-06-20
 */
@Api(description = "会员信息基本表")
@RestController
@RequestMapping("/orgMember")
@Slf4j
public class OrgMemberController extends BaseController {

    @Autowired
    private OrgMemberService orgMemberService;

    @ApiOperation("分页")
    @GetMapping("/page")
    public RestResponse<IPage<OrgMember>> page(OrgMemberDto orgMemberDto) {
        return orgMemberService.page(orgMemberDto);
    }


    @ApiOperation("添加")
    @PostMapping("/add")
    public RestResponse<String> add(@Valid @RequestBody OrgMember orgMember) {
        return orgMemberService.add(orgMember);
    }

    @ApiOperation("修改")
    @PutMapping("/update")
    public RestResponse<String> update(@Valid @RequestBody OrgMember orgMember) {
        if(StringUtil.isNotEmpty(orgMember.getOrgId())){
            return orgMemberService.update(orgMember);
        }else {
            return ResultGenerator.genFailResult(DocConstant.update_error);
        }
    }

    @ApiOperation("删除")
    @DeleteMapping("/delete/{id}")
    public RestResponse<String> delete(@PathVariable String id) {
        if(StringUtil.isNotEmpty(id)){
            return orgMemberService.delete(id);
        }else {
            return ResultGenerator.genFailResult(DocConstant.delete_error);
        }
    }

    @GetMapping(value = "/listOrganizeMember")
    @ApiOperation("查询机构成员")
    public RestResponse<List<OrganizeInternalVo>> listOrganizeMember(String orgId) {
        if(StringUtil.isNotEmpty(orgId)){
            return orgMemberService.listOrganizeMember(orgId);
        }else {
            return ResultGenerator.genFailResult(DocConstant.search_error);
        }
    }

    @GetMapping(value = "/getOrgTransitionMember")
    @ApiOperation("获取换届机构成员信息")
    public RestResponse<List<OrganizeMemberVo>> getOrgTransitionMember(String orgId) {
        if(StringUtil.isNotEmpty(orgId)){
            return orgMemberService.getOrgTransitionMember(orgId);
        }else {
            return ResultGenerator.genFailResult(DocConstant.search_error);
        }
    }
}

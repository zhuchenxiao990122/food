package com.xw.project.controller.organize;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xw.common.result.RestResponse;
import com.xw.common.result.ResultGenerator;
import com.xw.project.dto.OrgPirmaryDto;
import com.xw.project.entity.OrgPrimary;
import com.xw.project.service.organize.OrgPrimaryService;
import com.xw.project.vo.OrganizeInternalVo;
import com.xw.project.vo.OrganizePrimaryVo;
import com.xw.system.entity.Organize;
import com.xw.system.vo.OrganizeTreeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 基层组织信息表
 * </p>
 *
 * @author dy
 * @since 2020-01-07
 */
@RestController
@Api(value = "基层组织")
@RequestMapping("/orgPrimary")
public class OrgPrimaryController {
    @Autowired
    private OrgPrimaryService orgPrimaryService;

    @GetMapping(value = {"/organizePrimary"})
    @ApiOperation(value = "获取基层组织信息")
    public RestResponse<IPage<OrganizePrimaryVo>> getOrganizePrimary(OrgPirmaryDto orgPirmaryDto) {
        return ResultGenerator.genSuccessResult(orgPrimaryService.organizePrimary(orgPirmaryDto));
    }

    @PostMapping(value = {"/addOrganizePrimary"})
    @ApiOperation(value = "新增基层组织信息")
    public RestResponse<String> addOrganizePrimary(@RequestBody OrganizePrimaryVo organizePrimaryVo ){
        return ResultGenerator.genSuccessResult(orgPrimaryService.addOrganizePrimary(organizePrimaryVo));
    }

    @PutMapping("/updateOrganization")
    @ApiOperation("修改基层组织信息")
    public RestResponse<String> updateOrganization(@RequestBody OrganizePrimaryVo organizePrimaryVo){
        return ResultGenerator.genSuccessResult(orgPrimaryService.updateOrganization(organizePrimaryVo));
    }

    @DeleteMapping("/{orgId}")
    @ApiOperation("删除基层组织信息")
    public RestResponse<String> deleteOrganization(@PathVariable(name = "orgId") String orgId){
        return ResultGenerator.genSuccessResult(orgPrimaryService.deleteOrganization(orgId));
    }

    @GetMapping("/obtainOrganzeCoding")
    @ApiOperation("获取基层机构编号")
    public RestResponse<String> obtainOrganzeCoding(@PathParam(value = "parentId") String parentId,
                                                    @PathParam(value = "dictId") String dictId){
        return ResultGenerator.genSuccessResult(orgPrimaryService.organzeGrasssCoding(parentId,dictId));
    }

    @GetMapping("/orgparimaryName")
    @ApiOperation("显示基层组织名称")
    public RestResponse<List<Organize>> orgparimaryName(@PathParam(value = "parentId") String parentId){
        return ResultGenerator.genSuccessResult(orgPrimaryService.orgparimaryName(parentId));
    }

}

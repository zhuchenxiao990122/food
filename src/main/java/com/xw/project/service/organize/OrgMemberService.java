package com.xw.project.service.organize;

import com.xw.common.result.RestResponse;
import com.xw.project.mapper.OrgMemberMapper;
import com.xw.project.service.organize.OrgMemberService;
import com.xw.project.entity.OrgMember;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xw.project.dto.OrgMemberDto;
import com.xw.project.vo.OrganizeInternalVo;
import com.xw.project.vo.OrganizeMemberVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface OrgMemberService {

    RestResponse<IPage<OrgMember>> page(OrgMemberDto orgMemberDto);

    RestResponse<String> add(OrgMember orgMember);

    RestResponse<String> update(OrgMember orgMember);

    RestResponse<String> delete(String id);

    RestResponse<List<OrganizeInternalVo>> listOrganizeMember(String orgId);

    RestResponse<List<OrganizeMemberVo>> getOrgTransitionMember(String orgId);
}

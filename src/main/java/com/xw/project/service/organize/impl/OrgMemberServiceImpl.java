package com.xw.project.service.organize.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xw.common.result.RestResponse;
import com.xw.common.result.ResultGenerator;
import com.xw.common.util.StringUtil;
import com.xw.core.constant.DocConstant;
import com.xw.project.dto.OrgMemberDto;
import com.xw.project.mapper.OrgMemberMapper;
import com.xw.project.service.organize.OrgMemberService;
import com.xw.project.entity.OrgMember;
import com.xw.project.vo.OrganizeInternalVo;
import com.xw.project.vo.OrganizeMemberVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * OrgMember服务类
 * </p>
 *
 * @author yuli
 * @since 2020-06-20
 */
@Slf4j
@Service
public class OrgMemberServiceImpl extends ServiceImpl<OrgMemberMapper, OrgMember> implements OrgMemberService {

    @Autowired
    private OrgMemberMapper orgMemberMapper;

    @Override
    public RestResponse<IPage<OrgMember>> page(OrgMemberDto orgMemberDto) {
        IPage<OrgMember> page = new Page<>(orgMemberDto.getCurrent(), orgMemberDto.getSize());
        QueryWrapper<OrgMember> queryWrapper = new QueryWrapper<>();
        return ResultGenerator.genSuccessResult(baseMapper.selectPage(page, queryWrapper));

    }

    @Override
    public RestResponse<String> add(OrgMember orgMember) {
        try{
            baseMapper.insert(orgMember);
            return  ResultGenerator.genSuccessResult(DocConstant.add_success);
        }catch (Exception e){
            log.error(DocConstant.add_error,e);
            return  ResultGenerator.genFailResult(DocConstant.add_error);
        }
    }

    @Override
    public RestResponse<String> update(OrgMember orgMember) {
        try{
            return ResultGenerator.genSuccessResult(baseMapper.updateById(orgMember));
        }catch (Exception e){
            log.error(DocConstant.update_error, e);
            return ResultGenerator.genFailResult(DocConstant.update_error);
        }
    }

    @Override
    public RestResponse<String> delete(String id) {
        try{
            baseMapper.deleteById(id);
            return ResultGenerator.genSuccessResult(DocConstant.delete_success);
        }catch (Exception e){
            log.error(DocConstant.delete_error, e);
            return ResultGenerator.genSuccessResult(DocConstant.delete_error);
        }
    }

    @Override
    public RestResponse<List<OrganizeInternalVo>> listOrganizeMember(String orgId) {
        return ResultGenerator.genSuccessResult(orgMemberMapper.listOrganizeMember(orgId));
    }

    /**
     * 查询要换届的组织成员
     * @param orgId
     * @return
     */
    @Override
    public RestResponse<List<OrganizeMemberVo>> getOrgTransitionMember(String orgId) {
        List<OrganizeInternalVo> organizeInternalVos;
        organizeInternalVos = orgMemberMapper.listOrganizeMember(orgId);

        if(organizeInternalVos.size() ==0 || organizeInternalVos == null){
            return null;
        }
        Iterator<OrganizeInternalVo> iterator = organizeInternalVos.iterator();
        Map<String, String> postMap = new HashMap<>();
        while (iterator.hasNext()) {
            OrganizeInternalVo next = iterator.next();
            if (!postMap.containsKey(next.getPost())) {
                postMap.put(next.getPost(), next.getMemberName());
                continue;
            }
            postMap.put(next.getPost(), StringUtil.join(postMap.get(next.getPost()), "\t", next.getMemberName()));
        }
        List<OrganizeMemberVo> organizeMemberVos = new ArrayList<>();
        Iterator<String> postMapIterator = postMap.keySet().iterator();
        while (postMapIterator.hasNext()) {
            String post = postMapIterator.next();
            OrganizeMemberVo organizeMemberVo = new OrganizeMemberVo();
            organizeMemberVo.setOrgId(orgId);
            organizeMemberVo.setPost(post);
            organizeMemberVo.setPostUserNames(postMap.get(post));
            organizeMemberVos.add(organizeMemberVo);
        }
        return ResultGenerator.genSuccessResult(organizeMemberVos);
    }

}

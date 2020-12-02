package com.xw.project.service.organize.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xw.common.result.RestResponse;
import com.xw.common.result.ResultGenerator;
import com.xw.common.util.StringUtil;
import com.xw.common.util.UUIDGenerator;
import com.xw.core.constant.DocConstant;
import com.xw.project.entity.OrgHistory;
import com.xw.project.entity.OrgMember;
import com.xw.project.mapper.OrgHistoryMapper;
import com.xw.project.mapper.OrgMemberMapper;
import com.xw.project.service.organize.OrgHistoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xw.project.service.organize.OrgMemberService;
import com.xw.project.vo.OrgHistoryVo;
import com.xw.project.vo.OrgInternalUserVo;
import com.xw.project.vo.OrganizeInternalVo;
import com.xw.system.entity.Organize;
import com.xw.system.mapper.OrganizeMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author caoshuhao
 * @since 2020-06-22
 */
@Service
public class OrgHistoryServiceImpl extends ServiceImpl<OrgHistoryMapper, OrgHistory> implements OrgHistoryService {

    @Autowired
    private OrgHistoryMapper orgHistoryMapper;
    @Autowired
    private OrgMemberMapper orgMemberMapper;
    @Autowired
    private OrganizeMapper organizeMapper;

    /**
     * 获取历届机构
     *
     * @param parentId
     * @return
     */
    @Override
    public RestResponse<List<OrgHistory>> listOrgHistory(String parentId) {
        QueryWrapper<OrgHistory> orgHistoryQueryWrapper = new QueryWrapper<>();
        orgHistoryQueryWrapper.lambda().eq(OrgHistory::getParentId, parentId);
        try {
            List<OrgHistory> orgHistories = orgHistoryMapper.selectList(orgHistoryQueryWrapper);
            return ResultGenerator.genSuccessResult(orgHistories);
        }catch (Exception e){
            log.error(DocConstant.search_error,e);
            return  ResultGenerator.genFailResult(DocConstant.search_error);
        }
    }

    @Override
    public RestResponse<List<OrgMember>> listOrganizeMember(String orgId) {
        QueryWrapper<OrgMember> orgMemberQueryWrapper = new QueryWrapper<>();
        orgMemberQueryWrapper.lambda().eq(OrgMember::getOrgId, orgId);
        try{
            List<OrgMember> orgMembers = orgMemberMapper.selectList(orgMemberQueryWrapper);
            return ResultGenerator.genSuccessResult(orgMembers);
        }catch (Exception e){
            log.error(DocConstant.search_error);
            return ResultGenerator.genFailResult(DocConstant.search_error);
        }
    }

    @Override
    @Transactional
    public RestResponse<String> delete(String id) {

        QueryWrapper<OrgMember> orgMemberQueryWrapper = new QueryWrapper<>();
        orgMemberQueryWrapper.lambda().eq(OrgMember::getOrgId, id);
        try{
            orgHistoryMapper.deleteById(id);
            orgMemberMapper.delete(orgMemberQueryWrapper);
            return ResultGenerator.genSuccessResult(DocConstant.delete_success);
        }catch (Exception e){
            log.error(DocConstant.delete_error);
            return  ResultGenerator.genFailResult(DocConstant.delete_error);
        }

    }

    /**
     * 修改历届组织信息
     *
     * @param orgHistoryVo
     * @return
     */
    @Override
    @Transactional
    public RestResponse<String> updateHistory(OrgHistoryVo orgHistoryVo) {
        //修改机构信息
        try{
            OrgHistory orgHistory = orgHistoryMapper.selectById(orgHistoryVo.getOrgId());
            orgHistory.setName(orgHistoryVo.getFullName());
            orgHistory.setFullName(orgHistoryVo.getFullName());
            orgHistory.setSessionNum(orgHistoryVo.getSessionNum());
            orgHistory.setId(orgHistoryVo.getOrgId());
            orgHistoryMapper.updateById(orgHistory);
        }catch (Exception e){
            log.error(DocConstant.update_error);
            return ResultGenerator.genFailResult(DocConstant.update_error);
        }

        //修改成员信息
        List<OrgMember> orgMemberList = orgHistoryVo.getOrgMemberList();
        List<OrgMember> orgMemberArrayList = new ArrayList<>();
        QueryWrapper<OrgMember> qw = new QueryWrapper<>();
        qw.lambda().eq(OrgMember::getOrgId,orgHistoryVo.getOrgId()).eq(OrgMember::getDelFlag,"0");
        List<OrgMember> orgMembers = orgMemberMapper.selectList(qw);
        for(int i =0;i<orgMembers.size();i++){
            OrgMember orgMember = orgMembers.get(i);
            for(int j =0 ; j<orgMemberList.size();j++){
                OrgMember orgMember1 = orgMemberList.get(j);
                if(StringUtil.isNotEmpty(orgMember1)){
                    if(orgMember.getId().equals(orgMember1.getId())){
                        //内容为修改
                        orgMemberArrayList.add(orgMember1);
                        //修改
                        orgMemberMapper.updateById(orgMember1);
                        //剩余为新增
                        orgMemberList.remove(j--);
                        //剩余为删除
                        orgMembers.remove(i--);
                    }
                }
            }
       }

        //新增的成员添加id
        Iterator<OrgMember> orgMemberIterator = orgMemberList.iterator();
        while (orgMemberIterator.hasNext()) {
            OrgMember next = orgMemberIterator.next();
            next.setId(UUID.randomUUID().toString());
            next.setOrgId(orgHistoryVo.getOrgId());
            next.setDelFlag("0");
            next.setCreateDate(new Date());
            next.setUpdateDate(new Date());
        }
        try{
            //删除
            Iterator<OrgMember> iterator = orgMembers.iterator();
            while (iterator.hasNext()){
                orgMemberMapper.deleteById(iterator.next().getId());
            }
            //新增
            if (orgMemberList.size() > 0) {
                orgMemberMapper.addOrgMembers(orgMemberList);
            }
            return ResultGenerator.genSuccessResult(DocConstant.update_success);
        }catch (Exception e){
            log.error(DocConstant.update_error,e);
            return ResultGenerator.genFailResult(DocConstant.update_error);
        }

    }

    /**
     * 历届组织新增
     * @param orgHistoryVo
     * @return
     */
    @Override
    public RestResponse<String> addOrgHistory(OrgHistoryVo orgHistoryVo) {
        OrgHistory orgHistory = new OrgHistory();
        orgHistory.setParentId(orgHistoryVo.getParentId());
        orgHistory.setFullName(orgHistoryVo.getFullName());
        orgHistory.setName(orgHistoryVo.getFullName());
        try{
            orgHistoryMapper.insert(orgHistory);
        }catch (Exception e){
            log.error(DocConstant.add_error);
            return ResultGenerator.genFailResult(DocConstant.add_error);
        }
        String id = orgHistory.getId();
        List<OrgMember> orgMemberList = orgHistoryVo.getOrgMemberList();
        for (OrgMember orgMember : orgMemberList) {
            orgMember.setId(UUIDGenerator.getUUID());
            orgMember.setDelFlag("0");
            orgMember.setOrgId(id);
        }
        try {
            if(orgMemberList.size()>0){
                orgMemberMapper.addOrgMembers(orgMemberList);
            }
            return ResultGenerator.genSuccessResult(DocConstant.add_success);
        }catch (Exception e){
            log.error(DocConstant.add_error,e);
            return ResultGenerator.genFailResult(DocConstant.add_error);
        }
    }

    @Transactional
    @Override
    public RestResponse<String> transition(OrgHistoryVo orgHistoryVo){

        String orgId = orgHistoryVo.getOrgId();
        String fullName = orgHistoryVo.getFullName();
        QueryWrapper<OrgHistory> orgHistoryQueryWrapper = new QueryWrapper<>();
        orgHistoryQueryWrapper.lambda().eq(OrgHistory::getParentId, orgHistoryVo.getParentId()).and(
                wrapper -> wrapper.eq(OrgHistory::getFullName, fullName).or().eq(OrgHistory::getName, fullName));
        Integer count = orgHistoryMapper.selectCount(orgHistoryQueryWrapper);
        if (count > 0) {
           return ResultGenerator.genFailResult(DocConstant.update_error);
        }
        Organize organize = organizeMapper.selectById(orgId);
        OrgHistory orgHistory = new OrgHistory();
        BeanUtils.copyProperties(organize, orgHistory);
        orgHistory.setSessionNum(orgHistoryVo.getSessionNum());
        orgHistory.setId(null);
        try{
            orgHistoryMapper.insert(orgHistory);
            String id = orgHistory.getId();
            List<OrganizeInternalVo> organizeInternalVos = orgMemberMapper.listOrganizeMember(orgId);
            Iterator<OrganizeInternalVo> iterator = organizeInternalVos.iterator();
            List<OrgMember> orgMembers = new ArrayList<>();
            while (iterator.hasNext()) {
                OrganizeInternalVo next = iterator.next();
                OrgMember orgMember = new OrgMember();
                BeanUtils.copyProperties(next, orgMember);
                orgMember.setOrgId(id);
                orgMember.setDelFlag("0");
                orgMember.setId(UUID.randomUUID().toString());
                orgMembers.add(orgMember);
            }
            if (orgMembers.size() > 0) {
                orgMemberMapper.addOrgMembers(orgMembers);
            }
            return ResultGenerator.genSuccessResult(DocConstant.update_success);
        }catch (Exception e){
            log.error(DocConstant.update_error);
            return ResultGenerator.genFailResult(DocConstant.update_error);
        }
    }
}

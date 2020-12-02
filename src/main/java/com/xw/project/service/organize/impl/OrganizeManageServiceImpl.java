package com.xw.project.service.organize.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xw.common.entity.User;
import com.xw.common.result.RestResponse;
import com.xw.common.result.ResultGenerator;
import com.xw.common.util.StringUtil;
import com.xw.core.constant.DocConstant;
import com.xw.project.entity.OrgMember;

import com.xw.project.entity.OrgRedCross;
import com.xw.project.entity.TreeNode;
import com.xw.project.mapper.OrgMemberMapper;
import com.xw.project.mapper.OrgRedCrossMapper;
import com.xw.project.mapper.OrganizeManageMapper;
import com.xw.project.service.organize.OrganizeManageService;

import com.xw.project.vo.OrgInternalUserVo;
import com.xw.project.vo.OrganizeInternalVo;
import com.xw.system.entity.Organize;
import com.xw.system.exceptionhandler.SysException;
import com.xw.system.exceptionhandler.SysExceptionEnum;
import com.xw.system.mapper.OrganizeMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.runtime.directive.Define;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class OrganizeManageServiceImpl implements OrganizeManageService {
    @Autowired
    private OrganizeManageMapper organizeManageMapper;
    @Autowired
    private OrgRedCrossMapper orgRedCrossMapper;
    @Autowired
    private OrgMemberMapper orgMemberMapper;
    @Autowired
    private OrganizeMapper organizeMapper;

    @Override
    public List<TreeNode> listOrganize() {

        List<TreeNode> menus = organizeManageMapper.selectAllTrees();
        return createTree("0", menus);
    }

    @Override
    public OrgRedCross organizeDetailInfo(String treeId) {
        //插件有问题，根据主键查不到
        QueryWrapper<OrgRedCross> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("org_id", treeId);
        OrgRedCross orgRedCross = orgRedCrossMapper.organizeDetailInfo(treeId);
        return orgRedCross;
    }

    @Override
    public String updateOrganizeBase(OrgRedCross orgRedCross) {
        QueryWrapper<OrgRedCross> orgRedCrossQueryWrapper = new QueryWrapper<>();
        orgRedCrossQueryWrapper.lambda().eq(OrgRedCross::getOrgId, orgRedCross.getOrgId());
        List<OrgRedCross> orgRedCrosses = orgRedCrossMapper.selectList(orgRedCrossQueryWrapper);
        if (orgRedCrosses == null || orgRedCrosses.size() == 0) {
            orgRedCrossMapper.insert(orgRedCross);
        } else {
            orgRedCrossMapper.update(orgRedCross, orgRedCrossQueryWrapper);
        }
        return orgRedCross.getOrgId();
    }

    /**
     * 查询内部机构
     *
     * @param parentId
     * @return
     */
    @Override
    public RestResponse<List<OrganizeInternalVo>> listOrganizationUnit(String parentId, String orgType) {
        QueryWrapper<Organize> organizeQueryWrapper = new QueryWrapper<>();
        organizeQueryWrapper.lambda().eq(Organize::getOrgType, orgType).
                eq(Organize::getParentId, parentId).
                orderByDesc(Organize::getOrderBy).
                orderByDesc(Organize::getFullName);
        List<Organize> organizes;
        List<OrganizeInternalVo> organizeInternalVos;
        try {
            organizes = organizeMapper.selectList(organizeQueryWrapper);
            //查询有成员存在的组织机构
            organizeInternalVos = orgMemberMapper.internalMemberCount(parentId, orgType);
        } catch (Exception e) {
            log.error(DocConstant.search_error, e);
            return ResultGenerator.genFailResult(DocConstant.search_error);
        }
        //返回类型为OrganizeInternalVO类型
        List<OrganizeInternalVo> organizeInternalVoList = new ArrayList<>();
        for (Organize organize : organizes) {
            OrganizeInternalVo organizeInternalVo = new OrganizeInternalVo();
            BeanUtils.copyProperties(organize, organizeInternalVo);
            organizeInternalVo.setOrgName(organize.getFullName());
            organizeInternalVo.setId(organize.getId());
            organizeInternalVo.setOrgId(organize.getId());
            organizeInternalVo.setChildCount(0);
            organizeInternalVo.setHasChildren(false);
            organizeInternalVoList.add(organizeInternalVo);
        }
        if (organizeInternalVos.size() > 0) {
            Iterator<OrganizeInternalVo> iterator = organizeInternalVos.iterator();
            while (iterator.hasNext()) {
                OrganizeInternalVo next = iterator.next();
                for (OrganizeInternalVo organizeInternalvo : organizeInternalVoList) {
                    if (organizeInternalvo.getOrgId().equals(next.getOrgId())) {
                        organizeInternalvo.setHasChildren(true);
                        organizeInternalvo.setChildCount(next.getChildCount());
                        break;
                    }
                }
            }
        }
        return ResultGenerator.genSuccessResult(organizeInternalVoList);
    }

    /**
     * 删除组织机构
     *
     * @param orgId
     * @return
     */
    @Override
    public RestResponse<String> deleteInternalOrganize(String orgId) {
        QueryWrapper<OrgMember> orgMemberQueryWrapper = new QueryWrapper<>();
        orgMemberQueryWrapper.lambda().eq(OrgMember::getOrgId, orgId);
        try {
            organizeMapper.deleteById(orgId);
            orgMemberMapper.delete(orgMemberQueryWrapper);
            return ResultGenerator.genSuccessResult(DocConstant.delete_success);
        } catch (Exception e) {
            log.error(DocConstant.delete_error, e);
            return ResultGenerator.genFailResult(DocConstant.delete_error);
        }
    }

    /**
     * 修改机构名称或成员
     *
     * @param orgInternalUserVo
     * @return
     */
    @Override
    @Transactional
    public RestResponse<String> updateInternalOrganize(OrgInternalUserVo orgInternalUserVo) {
        //组织名称是否存在
        if (!detectOrgName(orgInternalUserVo)) {
            return ResultGenerator.genSuccessResult(DocConstant.update_error);
        }
        //机构信息修改  机构名称
        Organize organize = new Organize();
        organize.setId(orgInternalUserVo.getOrgId());
        organize.setFullName(orgInternalUserVo.getName());

        String orgId = orgInternalUserVo.getOrgId();
        QueryWrapper<OrgMember> orgMemberQueryWrapper = new QueryWrapper<>();
        orgMemberQueryWrapper.lambda().eq(OrgMember::getOrgId, orgId).eq(OrgMember::getDelFlag, "0");
        //数据库中机构成员
        List<OrgMember> orgMemberList = orgMemberMapper.selectList(orgMemberQueryWrapper);
        //前端传来机构成员
        List<OrganizeInternalVo> orgMembers = orgInternalUserVo.getData();

        for (int i = 0; i < orgMembers.size(); i++) {
            OrganizeInternalVo organizeInternalVo = orgMembers.get(i);
            for (int j = 0; j < orgMemberList.size(); j++) {
                OrgMember orgMember = orgMemberList.get(j);
                if (StringUtil.isNotEmpty(organizeInternalVo.getId())) {
                    if (organizeInternalVo.getId().equals(orgMember.getId())) {
                        OrgMember updateOrgMember = new OrgMember();
                        BeanUtils.copyProperties(organizeInternalVo, updateOrgMember);
                        orgMemberMapper.updateById(updateOrgMember);
                        //移除
                        orgMembers.remove(i--);
                        //
                        orgMemberList.remove(j--);
                    }
                }
            }
        }

        try {
            //修改机构信息
            organizeMapper.updateById(organize);
            //删除机构成员
            if (orgMemberList.size() > 0) {
                for (OrgMember orgMember : orgMemberList) {
                    orgMemberMapper.deleteById(orgMember.getId());
                }
            }
            //新增机构成员
            addOrgMembers(orgInternalUserVo, orgId);

        } catch (Exception e) {
            log.error(DocConstant.update_error, e);
            return ResultGenerator.genFailResult(DocConstant.update_error);
        }
        return ResultGenerator.genSuccessResult(DocConstant.update_success);
    }

    @Override
    public RestResponse<List<OrganizeInternalVo>> listOrgUser(String orgId) {
        try {
            return ResultGenerator.genSuccessResult(organizeManageMapper.listOrgUsers(orgId));
        } catch (Exception e) {
            log.error(DocConstant.search_error, e);
            return ResultGenerator.genFailResult(DocConstant.search_error);
        }
    }

    /**
     * 新增组织机构
     *
     * @param orgInternalUserVo
     * @return
     */
    @Override
    @Transactional
    public RestResponse<String> addInternalOrg(OrgInternalUserVo orgInternalUserVo) {
        if (!detectOrgName(orgInternalUserVo)) {
            return ResultGenerator.genFailResult(DocConstant.add_error);
        }
        //创建组织机构
        Organize organize = new Organize();
        organize.setOrgType(orgInternalUserVo.getPost());
        organize.setParentId(orgInternalUserVo.getParentId());
        organize.setName(orgInternalUserVo.getName());
        organize.setFullName(orgInternalUserVo.getName());
        try {
            organizeMapper.insert(organize);
            String orgId = organize.getId();
            //组织机构添加成员
            addOrgMembers(orgInternalUserVo, orgId);
        } catch (Exception e) {
            log.error(DocConstant.add_error, e);
            ResultGenerator.genFailResult(DocConstant.add_error);
        }
        return ResultGenerator.genSuccessResult(DocConstant.add_success);
    }

    /**
     * 检查同一个组织下，名称是否存在
     *
     * @param orgInternalUserVo
     * @return
     */
    public boolean detectOrgName(OrgInternalUserVo orgInternalUserVo) {
        QueryWrapper<Organize> organizeQueryWrapper = new QueryWrapper<>();
        organizeQueryWrapper.lambda().eq(Organize::getFullName, orgInternalUserVo.getName())
                .eq(Organize::getParentId, orgInternalUserVo.getParentId())
                .eq(Organize::getOrgType, orgInternalUserVo.getPost());
        //    .ne(Organize::getId,orgInternalUserVo.getId);
        Integer count = organizeMapper.selectCount(organizeQueryWrapper);
        if (count > 1) {
            return false;
        }
        return true;
    }

    /**
     * org_member表批量添加成员
     *
     * @param orgInternalUserVo
     * @param orgId
     */
    public void addOrgMembers(OrgInternalUserVo orgInternalUserVo, String orgId) {
        List<OrgMember> orgMembers = new ArrayList<>();
        List<OrganizeInternalVo> organizeInternalVos = orgInternalUserVo.getData();
        Iterator<OrganizeInternalVo> iterator = organizeInternalVos.iterator();
        while (iterator.hasNext()) {
            OrganizeInternalVo next = iterator.next();
            OrgMember orgMember = new OrgMember();
            BeanUtils.copyProperties(next, orgMember);
            orgMember.setId(UUID.randomUUID().toString());
            orgMember.setOrgId(orgId);
            orgMember.setDelFlag("0");
            orgMembers.add(orgMember);
        }
        if (orgMembers.size() > 0) {
            orgMemberMapper.addOrgMembers(orgMembers);
        }

    }

    /*
     * 递归组装树
     */
    private List<TreeNode> createTree(String pid, List<TreeNode> menus) {
        List<TreeNode> treeMenu = new ArrayList<>();
        for (TreeNode menu : menus) {
            if (pid.equals(menu.getParentId())) {
                treeMenu.add(menu);
                menu.setChildren(createTree(menu.getId(), menus));
            }
        }
        return treeMenu;
    }
}

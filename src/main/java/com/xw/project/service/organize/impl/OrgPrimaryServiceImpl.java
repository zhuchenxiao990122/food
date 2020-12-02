package com.xw.project.service.organize.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xw.common.util.StringUtil;
import com.xw.common.util.UUIDGenerator;
import com.xw.common.util.UserUtil;
import com.xw.core.constant.DocConstant;
import com.xw.project.dto.OrgPirmaryDto;
import com.xw.project.entity.OrgPrimary;
import com.xw.project.mapper.OrgPrimaryMapper;
import com.xw.project.service.organize.OrgPrimaryService;
import com.xw.project.vo.OrganizePrimaryVo;
import com.xw.system.entity.Organize;
import com.xw.system.exceptionhandler.SysException;
import com.xw.system.exceptionhandler.SysExceptionEnum;
import com.xw.system.mapper.OrganizeMapper;
import com.xw.system.vo.OrganizeTreeVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <p>
 * 基层组织信息表  服务实现类
 * </p>
 *
 * @author dy
 * @since 2020-01-07
 */
@Service
public class OrgPrimaryServiceImpl extends ServiceImpl<OrgPrimaryMapper, OrgPrimary> implements OrgPrimaryService {
    @Autowired
    private OrgPrimaryMapper orgPrimaryMapper;
    @Autowired
    private OrganizeMapper organizeMapper;


    /**
     * 查找基层组织
     * orgPirmaryDto 基层组织Dto
     *
     * @return
     */
    @Override
    public IPage<OrganizePrimaryVo> organizePrimary(OrgPirmaryDto orgPirmaryDto) {
        Page<OrganizePrimaryVo> pageInfo = new Page((long) orgPirmaryDto.getCurrent(), (long) orgPirmaryDto.getSize());

        IPage<OrganizePrimaryVo> organizePrimaryVos = this.orgPrimaryMapper.getOrganizePrimary(pageInfo, orgPirmaryDto.getParentId(), orgPirmaryDto.getFullName(), orgPirmaryDto.getOrgCoding());
        return organizePrimaryVos;
    }

    /**
     * 添加基层组织
     *
     * @param organizePrimaryVo 基层组织表单
     * @return
     */
    @Override
    public String addOrganizePrimary(OrganizePrimaryVo organizePrimaryVo) {
        Organize organize = new Organize();
        OrgPrimary orgPrimary = new OrgPrimary();
        BeanUtils.copyProperties(organizePrimaryVo, organize);
        BeanUtils.copyProperties(organizePrimaryVo, orgPrimary);
        //查询基层组织名称是否已经存在 ，orgCoding是否存在
        QueryWrapper<Organize> organizeQueryWrapper = new QueryWrapper<>();
        organizeQueryWrapper.lambda().eq(Organize::getFullName, organizePrimaryVo.getFullName())
                .or().eq(Organize::getOrgcoding, organizePrimaryVo.getOrgcoding());
        Organize selectOne = organizeMapper.selectOne(organizeQueryWrapper);
        if (selectOne != null) {
            //异常
            throw new SysException(SysExceptionEnum.ORG_STATUS_ERROR);
        }

        String orgId = UUIDGenerator.getUUID();
        organize.setId(orgId);
        //查询父组织
        Organize parentOrganize = organizeMapper.selectById(organizePrimaryVo.getParentId());
        if (parentOrganize == null) {
            throw new SysException(SysExceptionEnum.ORG_STATUS_ERROR);
        }
        organize.setParentIds(StringUtil.join(parentOrganize.getParentIds(), ',', parentOrganize.getId()));

        //基层组织默认是2
        organize.setOrgType("2");
        organize.setName(organizePrimaryVo.getFullName());
        organize.setDelFlag("0");
        //组织结构的编码前6位为areaId
        organize.setAreaId(organizePrimaryVo.getOrgcoding().substring(0, 6));
        orgPrimary.setOrgId(orgId);
        orgPrimary.setDelFlag("0");

        orgPrimaryMapper.insert(orgPrimary);
        organizeMapper.insert(organize);
        return DocConstant.add_success;
    }

    /**
     * 修改基层组织
     *
     * @param organizePrimaryVo 基层组织表单
     * @return
     */
    @Override
    public String updateOrganization(OrganizePrimaryVo organizePrimaryVo) {
        Organize organize = new Organize();
        OrgPrimary orgPrimary = new OrgPrimary();
        BeanUtils.copyProperties(organizePrimaryVo, organize);
        BeanUtils.copyProperties(organizePrimaryVo, orgPrimary);
        //组织名是否重复
        QueryWrapper<Organize> organizeQueryWrapper = new QueryWrapper<>();
        organizeQueryWrapper.lambda().eq(Organize::getFullName, organizePrimaryVo.getFullName())
                .ne(Organize::getId, organizePrimaryVo.getOrgId());
        Organize selectOne = organizeMapper.selectOne(organizeQueryWrapper);
        if (selectOne != null) {
            throw new SysException(SysExceptionEnum.ORG_STATUS_ERROR);
        }
        QueryWrapper<OrgPrimary> qw = new QueryWrapper<>();
        qw.lambda().eq(OrgPrimary::getOrgId, organizePrimaryVo.getOrgId());
        organize.setName(organizePrimaryVo.getFullName());
        organize.setId(organizePrimaryVo.getOrgId());
        orgPrimary.setOrgId(organizePrimaryVo.getOrgId());
        organizeMapper.updateById(organize);
        orgPrimaryMapper.update(orgPrimary, qw);
        return organize.getId();
    }

    /**
     * 删除基层组织
     *
     * @param orgId 基层组织Id
     * @return
     */
    @Override
    public String deleteOrganization(String orgId) {

        if (StringUtil.isNotEmpty(orgId)) {
            organizeMapper.deleteById(orgId);
            QueryWrapper<OrgPrimary> qw = new QueryWrapper<>();
            qw.lambda().eq(OrgPrimary::getOrgId, orgId);
            OrgPrimary orgPrimary = orgPrimaryMapper.selectOne(qw);
            orgPrimary.setDelFlag("1");
            orgPrimaryMapper.update(orgPrimary, qw);
            return orgId;
        }
        return "删除失败";
    }

    /**
     * 生成基层组织编码
     *
     * @param parentId 所属组织的id
     * @param dictId   机构类型Id
     * @return
     */
    @Override
    public String organzeGrasssCoding(String parentId, String dictId) {
        System.out.println(dictId);
        String substring = dictId.substring(dictId.length() - 1);
        QueryWrapper<Organize> organizeQW = new QueryWrapper<>();
        organizeQW.eq("id", parentId);
        Organize parentOrganize = organizeMapper.selectOne(organizeQW);
        //如果父组织不存在
        if (parentOrganize == null) {
            throw new SysException(SysExceptionEnum.ORG_STATUS_ERROR);
        }
        String areaId = parentOrganize.getAreaId();
        String join = StringUtil.join(areaId, "2", substring);
        String bigCoding = orgPrimaryMapper.selectBigCoding(join);

        if (!StringUtil.isNotEmpty(bigCoding)) {
            return StringUtil.join(join, "01");
        }

        int coding = Integer.valueOf(bigCoding.substring(bigCoding.length() - 2)) + 1;
        if (coding < 10) {
            return StringUtil.join(join, '0', coding);
        }
        return StringUtil.join(join, coding);
    }

    /**
     * 查询基层组织的名称、id
     *
     * @return
     */
    @Override
    public List<Organize> orgparimaryName(String parentId) {
        QueryWrapper<Organize> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().select(Organize::getId, Organize::getFullName);
        queryWrapper.lambda().eq(Organize::getParentId, parentId).eq(Organize::getOrgType, '2');
        List<Organize> organizes = organizeMapper.selectList(queryWrapper);
        return organizes;
    }

}

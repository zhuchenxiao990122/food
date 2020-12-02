package com.xw.project.mapper;

import com.xw.project.entity.OrgInternal;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xw.project.vo.OrganizeInternalVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 红十字机构内部组织表  Mapper 接口
 * </p>
 *
 * @author dy
 * @since 2020-01-07
 */
public interface OrgInternalMapper extends BaseMapper<OrgInternal> {

    List<OrganizeInternalVo> getOrganizeInternal(@Param("orgId") String orgId, @Param("post") String post);

    List<OrganizeInternalVo> orgInternalCount(String orgId,String orgType);

    List<OrganizeInternalVo> listorganizeMember(String orgId);

    List<OrganizeInternalVo> listOrgUsers(String orgId);

    void addOrgInteralUsers(@Param("orgInternals") List<OrgInternal> orgInternals);

    void deleteOrgInternal(String orgId);
}

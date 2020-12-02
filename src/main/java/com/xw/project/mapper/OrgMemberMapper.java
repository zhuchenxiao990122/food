package com.xw.project.mapper;

import com.xw.project.entity.OrgMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xw.project.vo.RedCrossMemberAdultVo;
import com.xw.project.vo.OrganizeInternalVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 会员信息基本表 Mapper 接口
 * </p>
 *
 * @author dy
 * @since 2020-03-23
 */
public interface OrgMemberMapper extends BaseMapper<OrgMember> {

    List<OrganizeInternalVo> listOrganizeMember(String orgId);

    List<RedCrossMemberAdultVo> selectPageVoInIds(List<String> resourceIds);


    List<OrganizeInternalVo> internalMemberCount(@Param("parentId") String parentId,@Param("orgType") String orgType);

    void deleteOrgMembers(String orgId);

    void addOrgMembers(@Param("orgMembers") List<OrgMember> orgMembers);
}

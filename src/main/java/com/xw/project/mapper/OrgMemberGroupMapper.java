package com.xw.project.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xw.project.entity.OrgMemberGroup;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xw.project.vo.RedCrossMemberGroupVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 团体会员 Mapper 接口
 * </p>
 *
 * @author dy
 * @since 2020-03-23
 */
public interface OrgMemberGroupMapper extends BaseMapper<OrgMemberGroup> {

    IPage<RedCrossMemberGroupVo> listOrgMemeberGroup(Page<RedCrossMemberGroupVo> pageInfo, @Param("orgId") String orgId);
}

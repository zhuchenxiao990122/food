package com.xw.project.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xw.project.entity.OrgMemberAdult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xw.project.vo.RedCrossMemberAdultVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * adult_member 成人会员表 Mapper 接口
 * </p>
 *
 * @author dy
 * @since 2020-03-23
 */
public interface OrgMemberAdultMapper extends BaseMapper<OrgMemberAdult> {
    IPage<RedCrossMemberAdultVo> listOrgMemeberAdult(Page<RedCrossMemberAdultVo> pageInfo, @Param("orgId") String orgId);

}

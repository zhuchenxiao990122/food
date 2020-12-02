package com.xw.project.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xw.project.entity.OrgPrimary;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xw.project.vo.OrganizePrimaryVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 基层组织信息表  Mapper 接口
 * </p>
 *
 * @author dy
 * @since 2020-03-23
 */
public interface OrgPrimaryMapper extends BaseMapper<OrgPrimary> {

    IPage<OrganizePrimaryVo> getOrganizePrimary(Page<OrganizePrimaryVo> var1,@Param("parentId") String parentId,@Param("fullName") String fullName, @Param("orgCoding")String orgCoding);

    String selectBigCoding(String join);
}

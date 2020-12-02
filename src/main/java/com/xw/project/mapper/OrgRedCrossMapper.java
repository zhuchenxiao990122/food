package com.xw.project.mapper;

import com.xw.project.entity.OrgRedCross;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import javax.validation.Valid;
import javax.ws.rs.Path;

/**
 * <p>
 * 红十字机构信息表 对sys_organize基本组织信息表进行扩展 Mapper 接口
 * </p>
 *
 * @author dy
 * @since 2020-03-23
 */
public interface OrgRedCrossMapper extends BaseMapper<OrgRedCross> {

    OrgRedCross organizeDetailInfo(@Param(value = "treeId") String treeId);
}

package com.xw.project.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xw.project.dto.DisMaterialWarehouseDto;
import com.xw.project.entity.DisMaterialWarehouse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xw.project.vo.DisMaterialWarehouseVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>救灾救援-仓库管理-仓库信息管理-Mapper 接口
 *
 * @author weiLiang
 * @since 2020-07-21
 */
public interface DisMaterialWarehouseMapper extends BaseMapper<DisMaterialWarehouse> {
    /**
     * 根据参数列表查找仓库信息
     *
     * @param page 页面的基本信息
     * @param disMaterialWarehouseDto 封装了查询条件
     * @return IPage<DisMaterialWarehouseVo> 返回仓库信息
     * */
    IPage<DisMaterialWarehouseVo> pageListWarehouseInfo(Page<DisMaterialWarehouse> page, @Param("warehouse") DisMaterialWarehouseDto disMaterialWarehouseDto);

    /**
     * 根据组织 Id 查找区域编号
     *
     * @param orgId 组织 Id
     * @return String 返回获取的区域编号
     * */
    String getAreaId(@Param("orgId") String orgId);
}

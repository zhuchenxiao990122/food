package com.xw.project.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xw.project.entity.DisDonatedMaterials;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xw.project.vo.DisDonatedMaterialsVo;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dy
 * @since 2020-04-08
 */
public interface DisDonatedMaterialsMapper extends BaseMapper<DisDonatedMaterials> {

    IPage<DisDonatedMaterialsVo> pagesList(Page<DisDonatedMaterialsVo> pageInfo);
}

package com.xw.project.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xw.project.entity.DisStockIn;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xw.project.vo.DisStockInVo;
import com.xw.project.vo.DisStockWaitInVo;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yuli
 * @since 2020-04-13
 */
public interface DisStockInMapper extends BaseMapper<DisStockIn> {

    IPage<DisStockWaitInVo> getWaitInList(Page<DisStockWaitInVo> pageInfo);

    IPage<DisStockInVo> getInList(Page<DisStockInVo> pageInfo);
}

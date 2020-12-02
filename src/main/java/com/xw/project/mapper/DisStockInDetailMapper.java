package com.xw.project.mapper;

import com.xw.project.entity.DisStockInDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xw.project.vo.DisDonatedMaterialDetailVo;
import com.xw.project.vo.DisDonatedMaterialsVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yuli
 * @since 2020-04-13
 */
public interface DisStockInDetailMapper extends BaseMapper<DisStockInDetail> {

    List<DisDonatedMaterialDetailVo> getWaitInListDetails(String noticeId);
}

package com.xw.project.service.disaster;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xw.project.entity.DisStockIn;
import com.xw.project.vo.DisDonatedMaterialDetailVo;
import com.xw.project.vo.DisStockInVo;
import com.xw.project.vo.DisStockWaitInVo;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yuli
 * @since 2020-04-13
 */
public interface DisStockInService extends IService<DisStockIn> {

    IPage<DisStockWaitInVo> getWaitInList(Integer current, Integer size);

    IPage<DisStockInVo> getInList(Integer current, Integer size);

    int stockIn(Map<String, Object> disDonatedMaterialDetailVo);
}

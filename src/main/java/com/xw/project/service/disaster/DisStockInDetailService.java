package com.xw.project.service.disaster;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xw.project.entity.DisStockInDetail;
import com.xw.project.vo.DisDonatedMaterialDetailVo;
import com.xw.project.vo.DisDonatedMaterialsVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yuli
 * @since 2020-04-13
 */
public interface DisStockInDetailService extends IService<DisStockInDetail> {

    List<DisDonatedMaterialDetailVo> getWaitInListDetails(String noticeId);
}

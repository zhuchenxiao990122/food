package com.xw.project.service.disaster.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xw.project.entity.DisStockInDetail;
import com.xw.project.mapper.DisStockInDetailMapper;
import com.xw.project.service.disaster.DisStockInDetailService;
import com.xw.project.vo.DisDonatedMaterialDetailVo;
import com.xw.project.vo.DisDonatedMaterialsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yuli
 * @since 2020-04-13
 */
@Service
public class DisStockInDetailServiceImpl extends ServiceImpl<DisStockInDetailMapper, DisStockInDetail> implements DisStockInDetailService {
@Autowired
private DisStockInDetailMapper disStockInDetailMapper;
    @Override
    public List<DisDonatedMaterialDetailVo> getWaitInListDetails(String noticeId) {
        List<DisDonatedMaterialDetailVo> waitInListDetails = disStockInDetailMapper.getWaitInListDetails(noticeId);
        return waitInListDetails;
    }
}

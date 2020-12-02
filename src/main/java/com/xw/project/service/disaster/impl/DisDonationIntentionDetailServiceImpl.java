package com.xw.project.service.disaster.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xw.common.result.RestResponse;
import com.xw.common.result.ResultGenerator;
import com.xw.core.constant.DocConstant;
import com.xw.project.dto.DisDonationIntentionDetailDto;
import com.xw.project.entity.DisDonationIntentionDetail;
import com.xw.project.mapper.DisDonationIntentionDetailMapper;
import com.xw.project.service.disaster.DisDonationIntentionDetailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * OthDonationIntentionDetail服务类
 * </p>
 *
 * @author yuli
 * @since 2020-07-14
 */
@Service
public class DisDonationIntentionDetailServiceImpl extends ServiceImpl<DisDonationIntentionDetailMapper, DisDonationIntentionDetail> implements DisDonationIntentionDetailService {

    @Override
    @Transactional
    public RestResponse<String> save(DisDonationIntentionDetailDto disDonationIntentionDetailDto) {
        try {
            //删除原有数据
            baseMapper.deleteByIntentionId(disDonationIntentionDetailDto.getIntentionId());
            //插入新数据
            if (disDonationIntentionDetailDto.getOthDonationIntentionDetails().size() == 0) {
                return ResultGenerator.genSuccessResult(DocConstant.add_success);
            }
            this.saveBatch(disDonationIntentionDetailDto.getOthDonationIntentionDetails());
        } catch (Exception e) {
            return ResultGenerator.genFailResult(DocConstant.add_error);
        }
        return ResultGenerator.genSuccessResult(DocConstant.add_success);
    }

    @Override
    public void update(DisDonationIntentionDetail othDonationIntentionDetail) {
        baseMapper.updateById(othDonationIntentionDetail);
    }

    @Override
    public void delete(String id) {
        baseMapper.deleteById(id);
    }

}
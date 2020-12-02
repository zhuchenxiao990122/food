package com.xw.project.mapper;

import com.xw.project.entity.DisDonationIntentionDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xw.project.vo.DonationIntentionDetailVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yuli
 * @since 2020-04-28
 */
public interface DisDonationIntentionDetailMapper extends BaseMapper<DisDonationIntentionDetail> {
    List<DonationIntentionDetailVo> getIntentionDetailList(String intentionId);

    void deleteByIntentionId(String intentionId);
}

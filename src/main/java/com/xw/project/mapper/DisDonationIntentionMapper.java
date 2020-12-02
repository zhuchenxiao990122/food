package com.xw.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xw.project.dto.DisDonationIntentionDto;
import com.xw.project.entity.DisDonationIntention;
import com.xw.project.vo.DisDonationIntentionVo;
import com.xw.project.vo.DonationIntentionVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yuli
 * @since 2020-04-28
 */
public interface DisDonationIntentionMapper extends BaseMapper<DisDonationIntention> {
    List<DonationIntentionVo> selectByreSourceIds(@Param("resourceIds") List<String> resourceIds);

    List<Map<String,Object>> selectIntentionList();

    IPage<DisDonationIntentionVo> selectPageByCondition(IPage<DisDonationIntentionVo> page, DisDonationIntentionDto disDonationIntentionDto);
}

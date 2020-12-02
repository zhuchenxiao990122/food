package com.xw.project.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xw.project.dto.DisDonationFundDto;
import com.xw.project.entity.DisDonationFund;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xw.project.vo.DisDonationFundVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yuli
 * @since 2020-07-16
 */
public interface DisDonationFundMapper extends BaseMapper<DisDonationFund> {

    IPage<DisDonationFundVo> selectPageByCondition(IPage<DisDonationFundVo> page, @Param("disDonationFundDto") DisDonationFundDto disDonationFundDto);
}

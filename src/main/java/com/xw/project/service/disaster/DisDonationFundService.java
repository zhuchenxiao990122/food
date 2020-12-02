package com.xw.project.service.disaster;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xw.common.result.RestResponse;
import com.xw.project.dto.DisDonationFundDto;
import com.xw.project.dto.DisDonationFundFileDto;
import com.xw.project.entity.DisDonationFund;
import com.xw.project.vo.DisDonationFundVo;

public interface  DisDonationFundService {

  IPage<DisDonationFundVo> page(DisDonationFundDto disDonationFundDto);

  RestResponse< String > add(DisDonationFundFileDto disDonationFundFileDto);

  void update(DisDonationFund disDonationFund);

  void delete(String id);

}
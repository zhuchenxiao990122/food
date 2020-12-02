package com.xw.project.service.disaster;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xw.common.result.RestResponse;
import com.xw.project.dto.DisDonationIntentionDetailDto;
import com.xw.project.entity.DisDonationIntentionDetail;

public interface DisDonationIntentionDetailService extends IService<DisDonationIntentionDetail> {

  RestResponse<String> save(DisDonationIntentionDetailDto disDonationIntentionDetailDto);

  void update(DisDonationIntentionDetail disDonationIntentionDetail);

  void delete(String id);

}
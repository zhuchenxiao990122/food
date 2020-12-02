package com.xw.project.service.disaster;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xw.common.result.RestResponse;
import com.xw.project.dto.DisDonationIntentionDto;
import com.xw.project.entity.ComFile;
import com.xw.project.entity.DisDonationIntention;
import com.xw.project.vo.DisDonationIntentionVo;

public interface DisDonationIntentionService {

    IPage<DisDonationIntentionVo> page(DisDonationIntentionDto disDonationIntentionDto);

    void update(DisDonationIntention disDonationIntention);

    void delete(String id);

    RestResponse<String> add(DisDonationIntention disDonationIntention);

    RestResponse<String> addFile(ComFile comFile);

    RestResponse<String> deleteFile(ComFile comFile);
}
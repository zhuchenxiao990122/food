package com.xw.project.service.disaster;

import com.xw.common.result.RestResponse;
import com.xw.project.dto.DisDonationProjectDetailDto;
import com.xw.project.entity.DisDonationProjectDetail;

import java.util.List;

public interface DisDonationProjectDetailService {

    void update(DisDonationProjectDetail disDonationProjectDetail);

    void delete(String id);

    RestResponse save(DisDonationProjectDetailDto disDonationProjectDetailDto);

    List<DisDonationProjectDetail> search(String projectId);
}